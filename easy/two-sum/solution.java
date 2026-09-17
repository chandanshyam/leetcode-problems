/*
1. Two Sum   [Easy]
https://leetcode.com/problems/two-sum/

Runtime: 5 ms   Memory: 44.7 MB

You are given an array of integers `nums` and an integer `target`, return _indices of the two numbers such that they add up to `target`_.

You may assume that each input would have **_exactly_ one solution**, and you may not use the _same_ element twice.

You can return the answer in any order.

**Example 1:**

**Input:** nums = \[2,7,11,15\], target = 9
**Output:** \[0,1\]
**Explanation:** Because nums\[0\] + nums\[1\] == 9, we return \[0, 1\].

**Example 2:**

**Input:** nums = \[3,2,4\], target = 6
**Output:** \[1,2\]

**Example 3:**

**Input:** nums = \[3,3\], target = 6
**Output:** \[0,1\]

**Constraints:**

*   `2 <= nums.length <= 104`
*   `-109 <= nums[i] <= 109`
*   `-109 <= target <= 109`
*   **Only one valid answer exists.**

**Follow-up:** Can you come up with an algorithm that is less than `O(n2)` time complexity?
*/

import java.util.HashMap;
import java.util.ArrayList; 

class Solution {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> m = new HashMap<>();

        int[] res = new int[2];

        for(int i =0;i< nums.length;i++)
        {
            
            // add the index as key and the difference as the value in the map 
            // put the difference as the key and index as the value in the map
            //if the key already exists return i and value[key]

            if(m.containsKey(nums[i])){
                res[0] = m.get(nums[i]);
                res[1] = i;
            }
            else{
                m.put(target-nums[i], i);
            }
        
        }

        return res;
        
    }
}
