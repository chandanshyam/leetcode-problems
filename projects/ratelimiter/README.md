# Rate Limiter   [INTERMEDIATE]

https://leetcode.com/project/rate-limiter/

Tests: 18/18 passed   Submitted: 2026-09-24T06:34:17+00:00

### Background
Build an in-memory rate limiter for an LLM API gateway where multiple clients share API resources. The limiter must prevent a single client from consuming excessive capacity while safely handling concurrent requests and shared state.

### Task
Implement a rate limiter with two configurable strategies: **Sliding Window** and **Token Bucket**. The limiter should maintain independent state for each client and resource, correctly determine whether each request should be accepted, and return the remaining capacity and estimated recovery time. The implementation must also maintain accurate statistics and remain correct under concurrent access.

### What This Problem Tests
- Implementing **Sliding Window** and **Token Bucket** rate-limiting algorithms
- Handling time-based expiration and continuous token refills
- Managing **shared state and thread safety** in a concurrent environment
- Applying basic **locking and synchronization** mechanisms
- Maintaining isolated state across clients and resources
- Handling edge cases such as rejected requests, exhausted capacity, and partial recovery
- Translating detailed algorithmic requirements into a reliable engineering implementation

---

# Review

## Summary

The submission passes all automated tests with a correct, isolated, thread-safe rate limiter, and the highest-priority improvement is strengthening the reflection around atomic read-modify-write and local monotonic elapsed-time reasoning.

## Case Analysis

All automated tests pass, so there are no failed cases to repair. Remaining boundary observations are that stats updates are not in the same critical section as per-key decisions, `_EPS` can bias millisecond truncation very close to integer boundaries, and the reflection could more directly justify atomic read-modify-write and monotonic elapsed-time handling.

## System Design (excellent)

- 👍 Per-key state with per-key locks plus a guarded state map isolates `(client_id, endpoint)` and lets unrelated keys proceed concurrently.
- 👍 Policy A uses monotonic millisecond timestamps in a deque, expires old entries before deciding, and matches sliding-window semantics.
- 👍 Policy B tracks fractional tokens and last refill under lock, caps at capacity, and avoids rounding before the accept or reject decision.
- 👍 The distributed evolution discussion is credible and concrete, covering Redis Lua atomicity, sorted sets, hashes, TTLs, stats, async clients, and failure modes.
- ⚠️ Aggregate counters are updated after the per-key decision lock is released, so `stats()` can be momentarily stale relative to limiter state under concurrent checks.
- ⚠️ The reflection does not explicitly explain why the full read-modify-write sequence must be atomic, and it does not clearly justify local use of monotonic time.

## Code Quality (excellent)

- 👍 Separate `_WindowState` and `_BucketState` classes with `__slots__` make state ownership clear and focused.
- 👍 Control flow is direct, validation, expiry, decision, and counter paths are easy to trace, and public methods remain readable.
- 👍 Naming and return contracts align well with the server integration and hidden behavior expectations.
- ⚠️ `_EPS` is applied before integer truncation, which can technically turn a value just below an integer millisecond into the next integer instead of truncating toward zero.
- ⚠️ `Union[_WindowState, _BucketState]` plus a `pyright` ignore weakens type clarity; a small protocol or separate state maps would be cleaner.

## Comments

### Strengths

The implementation satisfies both policy behaviors and passes the full automated suite, which shows the core timer and remaining logic is reliable. The per-key locking design is a strong choice because it prevents same-key races while avoiding unnecessary contention across unrelated clients and endpoints. The distributed evolution essay is practical and covers Redis structures, atomic scripts, TTL cleanup, stats movement, and failure-mode tradeoffs.

### Improvements

Aggregate stats are updated after releasing the per-key lock, so concurrent `stats()` calls can observe stale totals during active decisions. Move counter updates into the same critical section as the decision, or document and test the intended consistency model. The reflection should explicitly state why the entire read-modify-write transaction must be atomic and why `time.monotonic()` is preferred for elapsed-time calculations. `_EPS` before truncation and the union type plus `pyright` ignore are minor quality issues; tighten truncation semantics and improve type separation if maintaining this code further.

### Advanced

Implement a deterministic clock test harness using the existing injectable clock. Walk both policies across exact millisecond boundaries, assert truncation rules for `reset_after_ms`, and exercise capacity caps, zero refill, and repeated rejections. Then add bounded cleanup for inactive keys, such as a `last_seen` timestamp and periodic eviction or TTL-like policy, and verify stats after eviction. This would harden boundary precision and prevent unbounded memory growth as client and endpoint cardinality increases.
