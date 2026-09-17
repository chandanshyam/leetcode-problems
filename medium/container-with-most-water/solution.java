/*
11. Container With Most Water   [Medium]
https://leetcode.com/problems/container-with-most-water/

Runtime: 5 ms   Memory: 58.2 MB

You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the `ith` line are `(i, 0)` and `(i, height[i])`.

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return _the maximum amount of water a container can store_.

**Notice** that you may not slant the container.

**Example 1:**

![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg)

**Input:** height = \[1,8,6,2,5,4,8,3,7\]
**Output:** 49
**Explanation:** The above vertical lines are represented by array \[1,8,6,2,5,4,8,3,7\]. In this case, the max area of water (blue section) the container can contain is 49.

**Example 2:**

**Input:** height = \[1,1\]
**Output:** 1

**Constraints:**

*   `n == height.length`
*   `2 <= n <= 105`
*   `0 <= height[i] <= 104`
*/

class Solution {
    public int maxArea(int[] height) {

        int l =0;
        int r = height.length -1;
        int maxC = 0;

        while(l < r)
        {

            int a = (r-l) * Math.min(height[r], height[l]);
            maxC = Math.max(maxC, a);


            if(height[l] > height[r])
            {
                r--;
            }
            else{
                l++;
            }


        }
        
        return maxC;
    }
}
