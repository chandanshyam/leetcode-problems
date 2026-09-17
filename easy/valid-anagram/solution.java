/*
242. Valid Anagram   [Easy]
https://leetcode.com/problems/valid-anagram/

Runtime: 4 ms   Memory: 43.1 MB

Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

**Example 1:**

**Input:** s = "anagram", t = "nagaram"

**Output:** true

**Example 2:**

**Input:** s = "rat", t = "car"

**Output:** false

**Constraints:**

*   `1 <= s.length, t.length <= 5 * 104`
*   `s` and `t` consist of lowercase English letters.

**Follow up:** What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
*/

class Solution {
    public boolean isAnagram(String s, String t) {
         int chars[] = new int[26];
        for(int i=0;i<s.length();i++){
            chars[s.charAt(i)-'a']++;
        }
        for(int i=0;i<t.length();i++){
            chars[t.charAt(i)-'a']--;
        }
        for(int i=0;i<26;i++){
            if(chars[i]!=0) return false;
        }
        return true;
    }
}
