/*
22. Generate Parentheses   [Medium]
https://leetcode.com/problems/generate-parentheses/

Runtime: 1 ms   Memory: 54.4 MB

Given `n` pairs of parentheses, write a function to _generate all combinations of well-formed parentheses_.

**Example 1:**

**Input:** n = 3
**Output:** \["((()))","(()())","(())()","()(())","()()()"\]

**Example 2:**

**Input:** n = 1
**Output:** \["()"\]

**Constraints:**

*   `1 <= n <= 8`
*/

/**
 * @param {number} n
 * @return {string[]}
 */
function generateParenthesis(n) {
    let res = [];
    generate("", n, n, res);
    return res;
}
function generate(str, left, right, res) {
    if (!left && !right && str.length) {
        res.push(str);
        return;
    }
    if (left) generate(str + "(", left - 1, right, res);
    if (right > left) generate(str + ")", left, right - 1, res);
}
