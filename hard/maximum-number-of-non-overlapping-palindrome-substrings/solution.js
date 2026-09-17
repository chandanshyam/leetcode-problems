/*
2472. Maximum Number of Non-overlapping Palindrome Substrings   [Hard]
https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/

Runtime: 2 ms   Memory: 54.7 MB

You are given a string `s` and a **positive** integer `k`.

Select a set of **non-overlapping** substrings from the string `s` that satisfy the following conditions:

*   The **length** of each substring is **at least** `k`.
*   Each substring is a **palindrome**.

Return _the **maximum** number of substrings in an optimal selection_.

A **substring** is a contiguous sequence of characters within a string.

**Example 1:**

**Input:** s = "abaccdbbd", k = 3
**Output:** 2
**Explanation:** We can select the substrings underlined in s = "**aba**cc**dbbd**". Both "aba" and "dbbd" are palindromes and have a length of at least k = 3.
It can be shown that we cannot find a selection with more than two valid substrings.

**Example 2:**

**Input:** s = "adbcda", k = 2
**Output:** 0
**Explanation:** There is no palindrome substring of length at least 2 in the string.

**Constraints:**

*   `1 <= k <= s.length <= 2000`
*   `s` consists of lowercase English letters.
*/

/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
 /**
 we have to do a center left right approach
 where each 
  */
var maxPalindromes = function (s, k) {
    const n = s.length;
    let ans = 0,
        start = 0;

    const check = (l, r) => {
        while (l < r) {
            if (s[l++] !== s[r--]) {
                return false;
            }
        }
        return true;
    };

    for (let r = k - 1; r < n; ++r) {
        let l = r - k + 1;
        if (l >= start && check(l, r)) {
            ++ans;
            start = r + 1;
            continue;
        }

        l = r - k;
        if (l >= start && check(l, r)) {
            ++ans;
            start = r + 1;
        }
    }

    return ans;
};
