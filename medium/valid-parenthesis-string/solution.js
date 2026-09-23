/*
678. Valid Parenthesis String   [Medium]
https://leetcode.com/problems/valid-parenthesis-string/

Runtime: 0 ms   Memory: 53.4 MB

Given a string `s` containing only three types of characters: `'('`, `')'` and `'*'`, return `true` _if_ `s` _is **valid**_.

The following rules define a **valid** string:

*   Any left parenthesis `'('` must have a corresponding right parenthesis `')'`.
*   Any right parenthesis `')'` must have a corresponding left parenthesis `'('`.
*   Left parenthesis `'('` must go before the corresponding right parenthesis `')'`.
*   `'*'` could be treated as a single right parenthesis `')'` or a single left parenthesis `'('` or an empty string `""`.

**Example 1:**

**Input:** s = "()"
**Output:** true

**Example 2:**

**Input:** s = "(\*)"
**Output:** true

**Example 3:**

**Input:** s = "(\*))"
**Output:** true

**Example 4:**

**Input:** s = "("
**Output:** false

**Constraints:**

*   `1 <= s.length <= 100`
*   `s[i]` is `'('`, `')'` or `'*'`.
*/

/**
 * @param {string} s
 * @return {boolean}
 */
var checkValidString = function(s) {

    const st = [];
    const sc = [];
    for(let i=0;i<s.length;i++)
    {
        if(s[i] === '(')
        {
            st.push(i);
        }
       else  if(s[i] === '*')
        {
            sc.push(i);
        }
          else
        {
            if(st.length > 0)
            {
                st.pop();

            }
            else if(sc.length > 0)
            {
                sc.pop();
            }
            else{
                return false;
            }
        }}

        while(st.length >0 && sc.length > 0)
        {
            const left = st.pop();
            const star = sc.pop();

            if(star < left)
            {
                return false;
            }
        }


        return st.length === 0;
    
};
