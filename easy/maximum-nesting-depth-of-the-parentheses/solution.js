/*
1614. Maximum Nesting Depth of the Parentheses   [Easy]
https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/

Runtime: 0 ms   Memory: 54.2 MB

Given a **valid parentheses string** `s`, return the **nesting depth** of `s`. The nesting depth is the **maximum** number of nested parentheses.

**Example 1:**

**Input:** s = "(1+(2\*3)+((8)/4))+1"

**Output:** 3

**Explanation:**

Digit 8 is inside of 3 nested parentheses in the string.

**Example 2:**

**Input:** s = "(1)+((2))+(((3)))"

**Output:** 3

**Explanation:**

Digit 3 is inside of 3 nested parentheses in the string.

**Example 3:**

**Input:** s = "()(())((()()))"

**Output:** 3

**Constraints:**

*   `1 <= s.length <= 100`
*   `s` consists of digits `0-9` and characters `'+'`, `'-'`, `'*'`, `'/'`, `'('`, and `')'`.
*   It is guaranteed that parentheses expression `s` is a VPS.
*/

/**
 * @param {string} s
 * @return {number}
 */
var maxDepth = function(s) {
    let depth = 0;
    let r = 0;
    for (const c of s) {
        if (c === ')') {
            depth--;
            continue;
        }
        // Digits and operators
        if (c !== '(') continue;
        depth++;
        // New max only possible after '('
        if (depth > r) r = depth;
    }
    return r;
};
