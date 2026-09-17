/*
115. Distinct Subsequences   [Hard]
https://leetcode.com/problems/distinct-subsequences/

Runtime: 8 ms   Memory: 58.9 MB

Given two strings s and t, return _the number of distinct_ **_subsequences_** _of_ s _which equals_ t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

**Example 1:**

**Input:** s = "rabbbit", t = "rabbit"
**Output:** 3
**Explanation:**
As shown below, there are 3 ways you can generate "rabbit" from s.
`**rabb**b**it**`
`**ra**b**bbit**`
`**rab**b**bit**`

**Example 2:**

**Input:** s = "babgbag", t = "bag"
**Output:** 5
**Explanation:**
As shown below, there are 5 ways you can generate "bag" from s.
`**ba**b**g**bag`
`**ba**bgba**g**`
`**b**abgb**ag**`
`ba**b**gb**ag**`
`babg**bag**`

**Constraints:**

*   `1 <= s.length, t.length <= 1000`
*   `s` and `t` consist of English letters.
*/

/**
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var numDistinct = function (s, t) {
    let memo = new Map();
    function dp(i, j) {
        if (i === s.length || j === t.length || s.length - i < t.length - j)
            return j === t.length ? 1 : 0;
        let key = [i, j].toString();
        if (memo.has(key)) return memo.get(key);
        let ans = dp(i + 1, j);
        if (s[i] === t[j]) ans += dp(i + 1, j + 1);
        memo.set(key, ans);
        return ans;
    }
    return dp(0, 0);
};
