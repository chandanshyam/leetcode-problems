/*
55. Jump Game   [Medium]
https://leetcode.com/problems/jump-game/

Runtime: 3 ms   Memory: 47.9 MB

You are given an integer array `nums`. You are initially positioned at the array's **first index**, and each element in the array represents your maximum jump length at that position.

Return `true` _if you can reach the last index, or_ `false` _otherwise_.

**Example 1:**

**Input:** nums = \[2,3,1,1,4\]
**Output:** true
**Explanation:** Jump 1 step from index 0 to 1, then 3 steps to the last index.

**Example 2:**

**Input:** nums = \[3,2,1,0,4\]
**Output:** false
**Explanation:** You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

**Constraints:**

*   `1 <= nums.length <= 104`
*   `0 <= nums[i] <= 105`
*/

class Solution {
    public boolean canJump(int[] nums) {

        int maxR =0;

        for(int i=0 ; i< nums.length; i++)
        {
            if(i > maxR) return false;
             maxR= Math.max(maxR, i + nums[i]);
        }
       
       return true;
        
        //check if you re
    }
}
