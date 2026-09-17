/*
940. Distinct Subsequences II   [Hard]
https://leetcode.com/problems/distinct-subsequences-ii/

Runtime: 2 ms   Memory: 54.4 MB

Given a string s, return _the number of **distinct non-empty subsequences** of_ `s`. Since the answer may be very large, return it **modulo** `109 + 7`.

A **subsequence** of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., `"ace"` is a subsequence of `"abcde"` while `"aec"` is not.

**Example 1:**

**Input:** s = "abc"
**Output:** 7
**Explanation:** The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".

**Example 2:**

**Input:** s = "aba"
**Output:** 6
**Explanation:** The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "aba".

**Example 3:**

**Input:** s = "aaa"
**Output:** 3
**Explanation:** The 3 distinct subsequences are "a", "aa" and "aaa".

**Constraints:**

*   `1 <= s.length <= 2000`
*   `s` consists of lowercase English letters.
*/

/**
 * @param {string} s
 * @return {number}
 */
const MOD = 1e9 + 7;

const distinctSubseqII = s => {
    let dp = new Int32Array(26), tot = 0;

    for (let i = 0; i < s.length; i++) {
        const c = s.charCodeAt(i) - 97;
        const add = (tot - dp[c] + MOD) % MOD;

        dp[c] = 1 + tot;
        tot = (dp[c] + add) % MOD;
    }

    return tot;
};
