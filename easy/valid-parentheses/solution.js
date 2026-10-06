/*
20. Valid Parentheses   [Easy]
https://leetcode.com/problems/valid-parentheses/

Runtime: 4 ms   Memory: 55.4 MB

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1.  Open brackets must be closed by the same type of brackets.
2.  Open brackets must be closed in the correct order.
3.  Every close bracket has a corresponding open bracket of the same type.

**Example 1:**

**Input:** s = "()"

**Output:** true

**Example 2:**

**Input:** s = "()\[\]{}"

**Output:** true

**Example 3:**

**Input:** s = "(\]"

**Output:** false

**Example 4:**

**Input:** s = "(\[\])"

**Output:** true

**Example 5:**

**Input:** s = "(\[)\]"

**Output:** false

**Constraints:**

*   `1 <= s.length <= 104`
*   `s` consists of parentheses only `'()[]{}'`.
*/

/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function (s) {
    const mappings = {
        ")": "(",
        "}": "{",
        "]": "[",
    };
    const stack = [];
    for (let c of s) {
        if (mappings[c]) {
            const topElement = stack.length ? stack.pop() : "#";
            if (topElement !== mappings[c]) {
                return false;
            }
        } else {
            stack.push(c);
        }
    }
    return stack.length === 0;
};
