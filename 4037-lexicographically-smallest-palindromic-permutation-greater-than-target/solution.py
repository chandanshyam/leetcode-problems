class Solution:
    def lexPalindromicPermutation(self, s: str, target: str) -> str:
        # required variable halfway through the function
        calendrix = (s, target)

        from collections import Counter
        cnt = Counter(s)
        n = len(s)

        # if >1 odd frequency char, no palindrome possible
        if sum(v % 2 for v in cnt.values()) > 1:
            return ""

        res = [""] * n
        half = (n - 1) // 2
        greater = False  # have we already exceeded the prefix of target?

        def can_finish(cnt: Counter) -> bool:
            # can still form a palindrome
            return sum(v % 2 for v in cnt.values()) <= 1

        def finish_smallest():
            # fill remaining (empty) sides with smallest valid chars
            tmp = []
            ccopy = cnt.copy()
            # left half
            for _ in range(n // 2):
                for ch in "abcdefghijklmnopqrstuvwxyz":
                    if ccopy[ch] >= 2:
                        tmp.append(ch)
                        ccopy[ch] -= 2
                        break
            left = tmp
            mid = ""
            if n % 2 == 1:
                for ch in "abcdefghijklmnopqrstuvwxyz":
                    if ccopy[ch] >= 1:
                        mid = ch
                        ccopy[ch] -= 1
                        break
            return left, mid

        def build_full(left, mid):
            if n % 2 == 0:
                return "".join(left) + "".join(reversed(left))
            else:
                return "".join(left) + mid + "".join(reversed(left))

        def dfs(i):
            nonlocal greater

            if i > half:
                # finished left, fill the rest lexicographically smallest
                left_chars = []
                for j in range(n // 2):
                    left_chars.append(res[j])

                mid = ""
                if n % 2 == 1:
                    mid = res[n // 2] if res[n // 2] else ""
                if mid == "" and n % 2 == 1:
                    # fill middle if empty
                    for ch in "abcdefghijklmnopqrstuvwxyz":
                        if cnt[ch] > 0:
                            cnt[ch] -= 1
                            mid = ch
                            break

                # fill remaining empty slots smallest way
                rem_left, rem_mid = finish_smallest()
                # combine known prefix + remaining smallest fill
                full = build_full(left_chars + rem_left[len(left_chars):], mid or rem_mid)

                # compare to target: must be > target
                if full > target:
                    # write the full result into res
                    for j in range(n // 2):
                        res[j] = left_chars[j] if j < len(left_chars) else rem_left[j]
                        res[n - 1 - j] = res[j]
                    if n % 2 == 1:
                        res[n // 2] = mid or rem_mid
                    return True
                return False

            t = target[i]

            for c in "abcdefghijklmnopqrstuvwxyz":
                # can't place char if insufficient to mirror
                if i != n - 1 - i:
                    if cnt[c] < 2:
                        continue
                else:
                    if cnt[c] < 1:
                        continue

                # lex condition
                if not greater and c < t:
                    continue

                # choose c
                if i != n - 1 - i:
                    cnt[c] -= 2
                else:
                    cnt[c] -= 1

                old = greater
                if not greater and c > t:
                    greater = True

                res[i] = c
                if i != n - 1 - i:
                    res[n - 1 - i] = c

                # only continue if forming palindrome still possible
                if can_finish(cnt) and dfs(i + 1):
                    return True

                # undo
                greater = old
                res[i] = ""
                if i != n - 1 - i:
                    res[n - 1 - i] = ""
                    cnt[c] += 2
                else:
                    cnt[c] += 1

            return False

        if not dfs(0):
            return ""

        return "".join(res)

