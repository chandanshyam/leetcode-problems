/*
5. Longest Palindromic Substring   [Medium]
https://leetcode.com/problems/longest-palindromic-substring/

Runtime: 16 ms   Memory: 54.3 MB

Given a string `s`, return _the longest_ _palindromic_ _substring_ in `s`.

**Example 1:**

**Input:** s = "babad"
**Output:** "bab"
**Explanation:** "aba" is also a valid answer.

**Example 2:**

**Input:** s = "cbbd"
**Output:** "bb"

**Constraints:**

*   `1 <= s.length <= 1000`
*   `s` consist of only digits and English letters.
*/

/**
 * @param {string} s
 * @return {string}
 */
var longestPalindrome = function(s) {
    let start =0;
    let end =0;
    for(let i =0; i < s.length ; i++)
    {
        for(let j=1;j<=2;j++)
        {
            let l = i;
            let r = i+j-1;

             while(l >= 0 && r < s.length && s[l] === s[r])
             {
                l--;
                r++;
             }

             if(r-l-1 > end - start)
             {
                start= l+1;
                end=r-1;
                
             }
             
        }
    }

    return s.substring(start, end+1);

};
