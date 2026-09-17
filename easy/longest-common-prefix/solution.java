/*
14. Longest Common Prefix   [Easy]
https://leetcode.com/problems/longest-common-prefix/

Runtime: 0 ms   Memory: 42 MB

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string `""`.

**Example 1:**

**Input:** strs = \["flower","flow","flight"\]
**Output:** "fl"

**Example 2:**

**Input:** strs = \["dog","racecar","car"\]
**Output:** ""
**Explanation:** There is no common prefix among the input strings.

**Constraints:**

*   `1 <= strs.length <= 200`
*   `0 <= strs[i].length <= 200`
*   `strs[i]` consists of only lowercase English letters if it is non-empty.
*/

class Solution {
    public String longestCommonPrefix(String[] strs) {

        if(strs == null || strs.length == 0)
        {
            return "";
        }

        String pref = strs[0];

        int prefLen = pref.length();

        for(int i=1;i<strs.length;i++)
        {
            String s = strs[i];
            while(prefLen > s.length() || !pref.equals(s.substring(0, prefLen)))
            {
                prefLen--;
                if(prefLen == 0)
                {
                    return "";
                }
                pref = pref.substring(0, prefLen);
            }
        }

        return pref;
    }
}
