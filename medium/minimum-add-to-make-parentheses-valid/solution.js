/*
921. Minimum Add to Make Parentheses Valid   [Medium]
https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

Runtime: 0 ms   Memory: 53.5 MB

A parentheses string is valid if and only if:

*   It is the empty string,
*   It can be written as `AB` (`A` concatenated with `B`), where `A` and `B` are valid strings, or
*   It can be written as `(A)`, where `A` is a valid string.

You are given a parentheses string `s`. In one move, you can insert a parenthesis at any position of the string.

*   For example, if `s = "()))"`, you can insert an opening parenthesis to be `"(**(**)))"` or a closing parenthesis to be `"())**)**)"`.

Return _the minimum number of moves required to make_ `s` _valid_.

**Example 1:**

**Input:** s = "())"
**Output:** 1

**Example 2:**

**Input:** s = "((("
**Output:** 3

**Constraints:**

*   `1 <= s.length <= 1000`
*   `s[i]` is either `'('` or `')'`.
*/

/**
 * @param {string} s
 * @return {number}
 */
var minAddToMakeValid = function(s) {
    let ob=0;
    let closed =0;

    for(let i=0;i<s.length;i++)
    {
        if(s[i] === "(")
        {
            ob+=1;
        }
        else if(s[i]===")" && ob>0)
        {
            ob-=1;
        }
        else{
            closed+=1;
        }
    }
    return ob + closed;
};
