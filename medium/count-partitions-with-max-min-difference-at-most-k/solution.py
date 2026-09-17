"""
3578. Count Partitions With Max-Min Difference at Most K   [Medium]
https://leetcode.com/problems/count-partitions-with-max-min-difference-at-most-k/

Runtime: 374 ms   Memory: 24.2 MB

You are given an integer array `nums` and an integer `k`. Your task is to partition `nums` into one or more **non-empty** contiguous segments such that in each segment, the difference between its **maximum** and **minimum** elements is **at most** `k`.

Return the total number of ways to partition `nums` under this condition.

Since the answer may be too large, return it **modulo** `109 + 7`.

**Example 1:**

**Input:** nums = \[9,4,1,3,7\], k = 4

**Output:** 6

**Explanation:**

There are 6 valid partitions where the difference between the maximum and minimum elements in each segment is at most `k = 4`:

*   `[[9], [4], [1], [3], [7]]`
*   `[[9], [4], [1], [3, 7]]`
*   `[[9], [4], [1, 3], [7]]`
*   `[[9], [4, 1], [3], [7]]`
*   `[[9], [4, 1], [3, 7]]`
*   `[[9], [4, 1, 3], [7]]`

**Example 2:**

**Input:** nums = \[3,3,4\], k = 0

**Output:** 2

**Explanation:**

There are 2 valid partitions that satisfy the given conditions:

*   `[[3], [3], [4]]`
*   `[[3, 3], [4]]`

**Constraints:**

*   `2 <= nums.length <= 5 * 104`
*   `1 <= nums[i] <= 109`
*   `0 <= k <= 109`
"""

class Solution:
    def countPartitions(self, nums, k):
        n=len(nums)
        MOD=10**9+7

        sum=[0]*(n+2)
        sum[1]=1

        qMax=deque()  
        qMin=deque() 

        l=0
        cnt=0

        for r, x in enumerate(nums):

            # max queue 
            while qMax and qMax[-1]<x:
                qMax.pop()
            qMax.append(x)

            # min queue 
            while qMin and qMin[-1]>x:
                qMin.pop()
            qMin.append(x)

            # shrink window
            while qMax[0]-qMin[0]>k:
                y=nums[l]
                if qMax[0]==y:
                    qMax.popleft()
                if qMin[0]==y:
                    qMin.popleft()
                l+=1

            #  update cnt & sum[r+2]
            cnt=(sum[r+1]-sum[l])%MOD
            sum[r+2]=(sum[r+1]+cnt)%MOD

        return cnt
