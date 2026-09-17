/*
1262. Greatest Sum Divisible by Three   [Medium]
https://leetcode.com/problems/greatest-sum-divisible-by-three/

Runtime: 4 ms   Memory: 49.5 MB

Given an integer array `nums`, return _the **maximum possible sum** of elements of the array such that it is divisible by three_.

**Example 1:**

**Input:** nums = \[3,6,5,1,8\]
**Output:** 18
**Explanation:** Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).

**Example 2:**

**Input:** nums = \[4\]
**Output:** 0
**Explanation:** Since 4 is not divisible by 3, do not pick any number.

**Example 3:**

**Input:** nums = \[1,2,3,4,4\]
**Output:** 12
**Explanation:** Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).

**Constraints:**

*   `1 <= nums.length <= 4 * 104`
*   `1 <= nums[i] <= 104`
*/

class Solution {
    public int maxSumDivThree(int[] nums) {
        int sum = 0;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min11 = Integer.MAX_VALUE;
        int min22 = Integer.MAX_VALUE;

        for (int x : nums) {
            sum += x;
            int r = x % 3;

            if (r == 1) {
                if (x < min1) { min11 = min1; min1 = x; }
                else if (x < min11) min11 = x;
            } 
            else if (r == 2) {
                if (x < min2) { min22 = min2; min2 = x; }
                else if (x < min22) min22 = x;
            }
        }

        int rem = sum % 3;

        if (rem == 0) return sum;

        if (rem == 1) {
            int remove1 = min1;
            int remove2 = (min2 == Integer.MAX_VALUE || min22 == Integer.MAX_VALUE)
                            ? Integer.MAX_VALUE : min2 + min22;
            int remove = Math.min(remove1, remove2);
            return (remove == Integer.MAX_VALUE) ? 0 : sum - remove;
        } 
        else {
            int remove1 = min2;
            int remove2 = (min1 == Integer.MAX_VALUE || min11 == Integer.MAX_VALUE)
                            ? Integer.MAX_VALUE : min1 + min11;
            int remove = Math.min(remove1, remove2);
            return (remove == Integer.MAX_VALUE) ? 0 : sum - remove;
        }
    }
}
