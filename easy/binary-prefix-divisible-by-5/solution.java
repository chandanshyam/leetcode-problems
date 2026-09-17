/*
1018. Binary Prefix Divisible By 5   [Easy]
https://leetcode.com/problems/binary-prefix-divisible-by-5/

Runtime: 3 ms   Memory: 47.6 MB

You are given a binary array `nums` (**0-indexed**).

We define `xi` as the number whose binary representation is the subarray `nums[0..i]` (from most-significant-bit to least-significant-bit).

*   For example, if `nums = [1,0,1]`, then `x0 = 1`, `x1 = 2`, and `x2 = 5`.

Return _an array of booleans_ `answer` _where_ `answer[i]` _is_ `true` _if_ `xi` _is divisible by_ `5`.

**Example 1:**

**Input:** nums = \[0,1,1\]
**Output:** \[true,false,false\]
**Explanation:** The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.
Only the first number is divisible by 5, so answer\[0\] is true.

**Example 2:**

**Input:** nums = \[1,1,1\]
**Output:** \[false,false,false\]

**Constraints:**

*   `1 <= nums.length <= 105`
*   `nums[i]` is either `0` or `1`.
*/

class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> res = new ArrayList<>();
        int val = 0;

        for (int n : nums) {
            val = ((val << 1) + n) % 5;
            res.add(val == 0);
        }

        return res;
    }
}
