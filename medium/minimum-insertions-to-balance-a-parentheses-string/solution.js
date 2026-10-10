/*
1541. Minimum Insertions to Balance a Parentheses String   [Medium]
https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/

Runtime: 10 ms   Memory: 58.6 MB

Given a parentheses string `s` containing only the characters `'('` and `')'`. A parentheses string is **balanced** if:

*   Any left parenthesis `'('` must have a corresponding two consecutive right parenthesis `'))'`.
*   Left parenthesis `'('` must go before the corresponding two consecutive right parenthesis `'))'`.

In other words, we treat `'('` as an opening parenthesis and `'))'` as a closing parenthesis.

*   For example, `"())"`, `"())(())))"` and `"(())())))"` are balanced, `")()"`, `"()))"` and `"(()))"` are not balanced.

You can insert the characters `'('` and `')'` at any position of the string to balance it if needed.

Return _the minimum number of insertions_ needed to make `s` balanced.

**Example 1:**

**Input:** s = "(()))"
**Output:** 1
**Explanation:** The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.

**Example 2:**

**Input:** s = "())"
**Output:** 0
**Explanation:** The string is already balanced.

**Example 3:**

**Input:** s = "))())("
**Output:** 3
**Explanation:** Add '(' to match the first '))', Add '))' to match the last '('.

**Constraints:**

*   `1 <= s.length <= 105`
*   `s` consists of `'('` and `')'` only.
*/

/**
 * @param {string} s
 * @return {number}
 */
var minInsertions = function(s) {
    /**
    we have to consider the order also 
    open always has to come before closed otherwise 
     */
     let open =0;
     let insertions=0;
     const length =s.length;
     let i=0;

   while(i < s.length)
   {
    if(s[i] ==='(')
    {
        open+=1
            i++;
    }
    else{
        if(open >0)
        {
            open--;
        }
        else{
            insertions++;
        }
        if(i<length-1 && s[i+1] ===")")
        {
            i+=2;
        }
        else{
            insertions++;
            i++;
        }
    }
   }
   insertions += open *2;
   return insertions;
    
};
