/*
1590. Make Sum Divisible by P   [Medium]
https://leetcode.com/problems/make-sum-divisible-by-p/

Runtime: 31 ms   Memory: 98.2 MB

Given an array of positive integers `nums`, remove the **smallest** subarray (possibly **empty**) such that the **sum** of the remaining elements is divisible by `p`. It is **not** allowed to remove the whole array.

Return _the length of the smallest subarray that you need to remove, or_ `-1` _if it's impossible_.

A **subarray** is defined as a contiguous block of elements in the array.

**Example 1:**

**Input:** nums = \[3,1,4,2\], p = 6
**Output:** 1
**Explanation:** The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray \[4\], and the sum of the remaining elements is 6, which is divisible by 6.

**Example 2:**

**Input:** nums = \[6,3,5,2\], p = 9
**Output:** 2
**Explanation:** We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray \[5,2\], leaving us with \[6,3\] with sum 9.

**Example 3:**

**Input:** nums = \[1,2,3\], p = 3
**Output:** 0
**Explanation:** Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.

**Constraints:**

*   `1 <= nums.length <= 105`
*   `1 <= nums[i] <= 109`
*   `1 <= p <= 109`
*/

class Solution {
    public int minSubarray(int[] nums, int p) {
        long total = 0;
        for (int num : nums) total += num;

        long target = total % p;
        if (target == 0) return 0;

        Map<Integer, Integer> mMap = new HashMap<>();
        mMap.put(0, -1);

        long prefix = 0;
        int res = nums.length;

        for (int i = 0; i < nums.length; i++) {
            prefix = (prefix + nums[i]) % p;
            int need = (int)((prefix - target + p) % p);

            if (mMap.containsKey(need)) {
                res = Math.min(res, i - mMap.get(need));
            }

            mMap.put((int)prefix, i);
        }

        return res == nums.length ? -1 : res;
    }
}
