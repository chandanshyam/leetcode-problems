import time
import threading
from collections import deque
from typing import Callable, Deque, Dict, Optional, Tuple, TypedDict, Union

Key = Tuple[str, str]
Decision = Tuple[bool, int, int]

# Absorbs float noise such as 799.9999999 -> 800 before truncating.
_EPS = 1e-9


class StatsDict(TypedDict):
    strategy: str
    total_allowed: int
    total_rejected: int
    active_keys: int


class _WindowState:
    """Policy A: accepted request timestamps (ms), oldest first."""

    __slots__ = ("lock", "timestamps")

    def __init__(self) -> None:
        self.lock = threading.Lock()
        self.timestamps: Deque[float] = deque()


class _BucketState:
    """Policy B: fractional token count plus time of last refill (ms)."""

    __slots__ = ("lock", "tokens", "last_ms")

    def __init__(self, capacity: float, now_ms: float) -> None:
        self.lock = threading.Lock()
        self.tokens = float(capacity)  # new buckets start full
        self.last_ms = now_ms


class RateLimiter:
    def __init__(
        self,
        strategy: str = "policy_a",
        clock: Optional[Callable[[], float]] = None,
    ):
        if strategy not in ("policy_a", "policy_b"):
            raise ValueError(f"Unknown strategy: {strategy}")

        self.strategy = strategy

        # _lock guards the state map (creating keys) and the counters.
        # Each key has its own lock for its decision, so different
        # (client_id, endpoint) pairs never wait on each other.
        self._lock = threading.Lock()
        self._state: Dict[Key, Union[_WindowState, _BucketState]] = {}

        self._total_allowed = 0
        self._total_rejected = 0

        # Monotonic clock in ms; injectable for deterministic tests.
        self._clock = clock or (lambda: time.monotonic() * 1000.0)

    def check(
        self,
        client_id: str,
        endpoint: str,
        limit: int,
        window_ms: int,
        capacity: int,
        refill_rate: float,
    ) -> Decision:
        """
        Run the configured policy and return (allowed, remaining, reset_after_ms).

        Policy A uses: limit, window_ms
        Policy B uses: capacity, refill_rate
        """
        if self.strategy == "policy_a":
            result = self.policy_a_limit(client_id, endpoint, limit, window_ms)
        else:
            result = self.policy_b_limit(client_id, endpoint, capacity, refill_rate)

        with self._lock:
            if result[0]:
                self._total_allowed += 1
            else:
                self._total_rejected += 1

        return result

    def policy_a_limit(
        self,
        client_id: str,
        endpoint: str,
        limit: int,
        window_ms: int,
    ) -> Decision:
        """
        Sliding window log: at most `limit` accepted requests in the most
        recent `window_ms` milliseconds.
        """
        st = self._get_state((client_id, endpoint), _WindowState)
        with st.lock:
            now = self._clock()
            ts = st.timestamps  # pyright: ignore[reportAttributeAccessIssue]

            # Expire before deciding. A request at t stops counting at t + window.
            while ts and ts[0] + window_ms <= now:
                ts.popleft()

            allowed = len(ts) < limit
            if allowed:
                ts.append(now)

            remaining = max(0, limit - len(ts)) if allowed else 0
            reset = int(max(0.0, ts[0] + window_ms - now) + _EPS) if ts else 0
            return allowed, remaining, reset

    def policy_b_limit(
        self,
        client_id: str,
        endpoint: str,
        capacity: int,
        refill_rate: float,
    ) -> Decision:
        """
        Token bucket: starts full at `capacity`, refills continuously at
        `refill_rate` tokens/sec up to `capacity`, each acceptance costs 1.
        """
        now_at_create = self._clock()
        st = self._get_state(
            (client_id, endpoint), lambda: _BucketState(capacity, now_at_create)
        )
        with st.lock:
            now = self._clock()
            elapsed_s = max(0.0, now - st.last_ms) / 1000.0
            st.tokens = min(float(capacity), st.tokens + elapsed_s * refill_rate)
            st.last_ms = max(st.last_ms, now)

            # Compare the unrounded budget; tolerance only covers float noise.
            if st.tokens >= 1.0 - _EPS:
                st.tokens = max(0.0, st.tokens - 1.0)
                return True, int(st.tokens + _EPS), 0

            if refill_rate > 0:
                reset = int((1.0 - st.tokens) * 1000.0 / refill_rate + _EPS)
            else:
                reset = 0  # capacity never recovers; any non negative value is valid
            return False, 0, max(0, reset)

    def stats(self) -> StatsDict:
        """
        Return aggregate limiter statistics.

        active_keys counts tracked (client_id, endpoint) entries for the active
        policy state.
        """
        with self._lock:
            return {
                "strategy": self.strategy,
                "total_allowed": self._total_allowed,
                "total_rejected": self._total_rejected,
                "active_keys": len(self._state),
            }

    def _get_state(self, key: Key, factory):
        # Create under the global lock so two threads can't race to make
        # separate states for the same key.
        with self._lock:
            st = self._state.get(key)
            if st is None:
                st = factory()
                self._state[key] = st
            return st