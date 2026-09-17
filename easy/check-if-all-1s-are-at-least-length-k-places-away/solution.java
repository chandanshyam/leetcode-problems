/*
1437. Check If All 1's Are at Least Length K Places Away   [Easy]
https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/

Runtime: 1 ms   Memory: 65.4 MB

Given an binary array `nums` and an integer `k`, return `true` _if all_ `1`_'s are at least_ `k` _places away from each other, otherwise return_ `false`.

**Example 1:**

![](https://assets.leetcode.com/uploads/2020/04/15/sample_1_1791.png)

**Input:** nums = \[1,0,0,0,1,0,0,1\], k = 2
**Output:** true
**Explanation:** Each of the 1s are at least 2 places away from each other.

**Example 2:**

![](https://assets.leetcode.com/uploads/2020/04/15/sample_2_1791.png)

**Input:** nums = \[1,0,0,1,0,1\], k = 2
**Output:** false
**Explanation:** The second 1 and third 1 are only one apart from each other.

**Constraints:**

*   `1 <= nums.length <= 105`
*   `0 <= k <= nums.length`
*   `nums[i]` is `0` or `1`
*/

class Solution {
    public boolean kLengthApart(int[] nums, int k) {
          int lastIndex = -1;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 1) {
                if (lastIndex != -1 && i - lastIndex - 1 < k) {
                    return false;
                }
                lastIndex = i;
            }
        }

        return true;

    }
}
