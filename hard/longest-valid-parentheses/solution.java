/*
32. Longest Valid Parentheses   [Hard]
https://leetcode.com/problems/longest-valid-parentheses/

Runtime: 4 ms   Memory: 45.2 MB

Given a string containing just the characters `'('` and `')'`, return _the length of the longest valid (well-formed) parentheses_ _substring_.

**Example 1:**

**Input:** s = "(()"
**Output:** 2
**Explanation:** The longest valid parentheses substring is "()".

**Example 2:**

**Input:** s = ")()())"
**Output:** 4
**Explanation:** The longest valid parentheses substring is "()()".

**Example 3:**

**Input:** s = ""
**Output:** 0

**Constraints:**

*   `0 <= s.length <= 3 * 104`
*   `s[i]` is `'('`, or `')'`.
*/

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max_len = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max_len = Math.max(max_len, i - stack.peek());
                }
            }
        }

        return max_len;        
    }
}
