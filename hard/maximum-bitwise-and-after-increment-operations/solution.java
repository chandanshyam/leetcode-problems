/*
3806. Maximum Bitwise AND After Increment Operations   [Hard]
https://leetcode.com/problems/maximum-bitwise-and-after-increment-operations/

Runtime: 115 ms   Memory: 50.2 MB

You are given an integer array `nums` and two integers `k` and `m`.

You may perform **at most** `k` operations. In one operation, you may choose any index `i` and **increase** `nums[i]` by 1.

Return an integer denoting the **maximum** possible **bitwise AND** of any **subset** of size `m` after performing up to `k` operations optimally.

**Example 1:**

**Input:** nums = \[3,1,2\], k = 8, m = 2

**Output:** 6

**Explanation:**

*   We need a subset of size `m = 2`. Choose indices `[0, 2]`.
*   Increase `nums[0] = 3` to 6 using 3 operations, and increase `nums[2] = 2` to 6 using 4 operations.
*   The total number of operations used is 7, which is not greater than `k = 8`.
*   The two chosen values become `[6, 6]`, and their bitwise AND is `6`, which is the maximum possible.

**Example 2:**

**Input:** nums = \[1,2,8,4\], k = 7, m = 3

**Output:** 4

**Explanation:**

*   We need a subset of size `m = 3`. Choose indices `[0, 1, 3]`.
*   Increase `nums[0] = 1` to 4 using 3 operations, increase `nums[1] = 2` to 4 using 2 operations, and keep `nums[3] = 4`.
*   The total number of operations used is 5, which is not greater than `k = 7`.
*   The three chosen values become `[4, 4, 4]`, and their bitwise AND is 4, which is the maximum possible.​​​​​​​

**Example 3:**

**Input:** nums = \[1,1\], k = 3, m = 2

**Output:** 2

**Explanation:**

*   We need a subset of size `m = 2`. Choose indices `[0, 1]`.
*   Increase both values from 1 to 2 using 1 operation each.
*   The total number of operations used is 2, which is not greater than `k = 3`.
*   The two chosen values become `[2, 2]`, and their bitwise AND is 2, which is the maximum possible.

**Constraints:**

*   `1 <= n == nums.length <= 5 * 104`
*   `1 <= nums[i] <= 109`
*   `1 <= k <= 109`
*   `1 <= m <= n`
*/

class Solution {
    public int maximumAND(int[] nums, int k, int m) {
        int ans =0;
        for(int bit =30; bit>=0; bit--)
            {
                int candidate = ans | (1<<bit);
                if(canAchieve(nums, k, m, candidate))
                {
                    ans = candidate;
                }
            }
        return ans;
    }

    private boolean canAchieve(int[]nums, int k, int m, int target)
    {
        long[] costs = new long[nums.length];
        for(int i =0; i < nums.length; i++)
            {
                costs[i] = costToSetBits(nums[i], target);
            }
            Arrays.sort(costs);
        long total =0;
        for(int i=0;i<m; i++)
            {
                total+=costs[i];
            }
            return total<=k;
    }

    private long costToSetBits(int val, int target){
        if(target ==0)
        {
            return 0;
        }

        if((val &target) == target)
        {
            return 0;
            
        }


        if(val <= target)
        {
            return target - val;
        }

       long x = val;
        
       while((x & target) != target)
           {
               long missing = target & ~x;
               int shift = Long.numberOfTrailingZeros(missing);
               x = ((x >> shift) + 1) << shift;
               
           }
        return x - val;
    }
}
