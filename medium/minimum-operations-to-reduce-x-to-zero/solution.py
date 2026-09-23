"""
1658. Minimum Operations to Reduce X to Zero   [Medium]
https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/

Runtime: 90 ms   Memory: 30.7 MB

You are given an integer array `nums` and an integer `x`. In one operation, you can either remove the leftmost or the rightmost element from the array `nums` and subtract its value from `x`. Note that this **modifies** the array for future operations.

Return _the **minimum number** of operations to reduce_ `x` _to **exactly**_ `0` _if it is possible__, otherwise, return_ `-1`.

**Example 1:**

**Input:** nums = \[1,1,4,2,3\], x = 5
**Output:** 2
**Explanation:** The optimal solution is to remove the last two elements to reduce x to zero.

**Example 2:**

**Input:** nums = \[5,6,7,8,9\], x = 4
**Output:** -1

**Example 3:**

**Input:** nums = \[3,2,20,1,1,3\], x = 10
**Output:** 5
**Explanation:** The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.

**Constraints:**

*   `1 <= nums.length <= 105`
*   `1 <= nums[i] <= 104`
*   `1 <= x <= 109`
"""

class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        total = sum(nums)
        n = len(nums)
        maxi = -1
        left = 0
        current = 0

        for right in range(n):
            # sum([left ,..., right]) = total - x
            current += nums[right]
            # if larger, move `left` to left
            while current > total-x and left <= right:
                current -= nums[left]
                left += 1
            # check if equal
            if current == total-x:
                maxi = max(maxi, right-left+1)

        return n-maxi if maxi != -1 else -1
