"""
3704. Count No-Zero Pairs That Sum to N   [Hard]
https://leetcode.com/problems/count-no-zero-pairs-that-sum-to-n/

Runtime: 2944 ms   Memory: 44 MB

A **no-zero** integer is a **positive** integer that **does not contain the digit** 0 in its decimal representation.

Given an integer `n`, count the number of pairs `(a, b)` where:

*   `a` and `b` are **no-zero** integers.
*   `a + b = n`

Return an integer denoting the number of such pairs.

**Example 1:**

**Input:** n = 2

**Output:** 1

**Explanation:**

The only pair is `(1, 1)`.

**Example 2:**

**Input:** n = 3

**Output:** 2

**Explanation:**

The pairs are `(1, 2)` and `(2, 1)`.

**Example 3:**

**Input:** n = 11

**Output:** 8

**Explanation:**

The pairs are `(2, 9)`, `(3, 8)`, `(4, 7)`, `(5, 6)`, `(6, 5)`, `(7, 4)`, `(8, 3)`, and `(9, 2)`. Note that `(1, 10)` and `(10, 1)` do not satisfy the conditions because 10 contains 0 in its decimal representation.

**Constraints:**

*   `2 <= n <= 1015`
"""

class Solution:
    def countNoZeroPairs(self, n: int) -> int:

        lenN = len(str(n))
        digits = list(map(int, str(n)))[::-1]

        @lru_cache(None)
        def solve(pos, carry, lenA, lenB):
            if pos == lenN:
                return int(carry == 0)

            rangeA = range(1, 10) if pos < lenA else (0,)
            rangeB = range(1, 10) if pos < lenB else (0,)

            numWays = 0
            for da in rangeA:
                for db in rangeB:
                    summ = da + db + carry
                    if summ % 10 == digits[pos]:
                        numWays += solve(pos + 1, summ // 10, lenA, lenB)
            return numWays

        totalPairs = 0
        for lenA in range(1, lenN + 1):
            for lenB in range(1, lenN + 1):
                totalPairs += solve(0, 0, lenA, lenB)

        return totalPairs
