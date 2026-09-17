/*
3. Longest Substring Without Repeating Characters   [Medium]
https://leetcode.com/problems/longest-substring-without-repeating-characters/

Runtime: 6 ms   Memory: 44.6 MB

Given a string `s`, find the length of the **longest** **substring** without duplicate characters.

**Example 1:**

**Input:** s = "abcabcbb"
**Output:** 3
**Explanation:** The answer is "abc", with the length of 3. Note that `"bca"` and `"cab"` are also correct answers.

**Example 2:**

**Input:** s = "bbbbb"
**Output:** 1
**Explanation:** The answer is "b", with the length of 1.

**Example 3:**

**Input:** s = "pwwkew"
**Output:** 3
**Explanation:** The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

**Constraints:**

*   `0 <= s.length <= 105`
*   `s` consists of English letters, digits, symbols and spaces.
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {

        int maxL = 0;
        Set<Character> set = new HashSet<>();

        int l=0;

        for(int r=0;r<s.length();r++)
        {
            while(set.contains(s.charAt(r)))
            {
                set.remove(s.charAt(l));
                l++;
            }

            set.add(s.charAt(r));

            maxL = Math.max(maxL, r-l+1);
        }

        return maxL;

}
}

//add p  r=1
//add w   r=2
//reach w again l=1
//set(has only p now)
// l=1
