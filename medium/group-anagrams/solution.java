/*
49. Group Anagrams   [Medium]
https://leetcode.com/problems/group-anagrams/

Runtime: 18 ms   Memory: 49.2 MB

Given an array of strings `strs`, group the anagrams together. You can return the answer in **any order**.

**Example 1:**

**Input:** strs = \["eat","tea","tan","ate","nat","bat"\]

**Output:** \[\["bat"\],\["nat","tan"\],\["ate","eat","tea"\]\]

**Explanation:**

*   There is no string in strs that can be rearranged to form `"bat"`.
*   The strings `"nat"` and `"tan"` are anagrams as they can be rearranged to form each other.
*   The strings `"ate"`, `"eat"`, and `"tea"` are anagrams as they can be rearranged to form each other.

**Example 2:**

**Input:** strs = \[""\]

**Output:** \[\[""\]\]

**Example 3:**

**Input:** strs = \["a"\]

**Output:** \[\["a"\]\]

**Constraints:**

*   `1 <= strs.length <= 104`
*   `0 <= strs[i].length <= 100`
*   `strs[i]` consists of lowercase English letters.
*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> ans= new HashMap<>();


        for(String s : strs)
        {
            int[] count = new int[26];
            for (char c: s.toCharArray())
            {
                

                count[c-'a']+=1;
            }

            String key = Arrays.toString(count);

            if(!ans.containsKey(key))
            {
                ans.put(key,new ArrayList<>());
            }
            ans.get(key).add(s);

        }

        return new ArrayList<>(ans.values());
        
    }
}
