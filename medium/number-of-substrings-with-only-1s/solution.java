/*
1513. Number of Substrings With Only 1s   [Medium]
https://leetcode.com/problems/number-of-substrings-with-only-1s/

Runtime: 5 ms   Memory: 46 MB

Given a binary string `s`, return _the number of substrings with all characters_ `1`_'s_. Since the answer may be too large, return it modulo `109 + 7`.

**Example 1:**

**Input:** s = "0110111"
**Output:** 9
**Explanation:** There are 9 substring in total with only 1's characters.
"1" -> 5 times.
"11" -> 3 times.
"111" -> 1 time.

**Example 2:**

**Input:** s = "101"
**Output:** 2
**Explanation:** Substring "1" is shown 2 times in s.

**Example 3:**

**Input:** s = "111111"
**Output:** 21
**Explanation:** Each substring contains only 1's characters.

**Constraints:**

*   `1 <= s.length <= 105`
*   `s[i]` is either `'0'` or `'1'`.
*/

class Solution {
    public int numSub(String s) {
        final int mod=1000000007;
        long cnt=0, ans=0;
        for(char c: s.toCharArray()){
            ans+=(1-(c-'0'))*cnt*(cnt+1)/2;
            cnt=(c-'0')*(cnt+1);
        }
        ans+=cnt*(cnt+1)/2;// last one
        return (int)(ans%mod);
    }
}
