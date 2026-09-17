/*
3397. Maximum Number of Distinct Elements After Operations   [Medium]
https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/

Runtime: 18 ms   Memory: 57.5 MB

You are given an integer array `nums` and an integer `k`.

You are allowed to perform the following **operation** on each element of the array **at most** _once_:

*   Add an integer in the range `[-k, k]` to the element.

Return the **maximum** possible number of **distinct** elements in `nums` after performing the **operations**.

**Example 1:**

**Input:** nums = \[1,2,2,3,3,4\], k = 2

**Output:** 6

**Explanation:**

`nums` changes to `[-1, 0, 1, 2, 3, 4]` after performing operations on the first four elements.

**Example 2:**

**Input:** nums = \[4,4,4,4\], k = 1

**Output:** 3

**Explanation:**

By adding -1 to `nums[0]` and 1 to `nums[1]`, `nums` changes to `[3, 5, 4, 4]`.

**Constraints:**

*   `1 <= nums.length <= 105`
*   `1 <= nums[i] <= 109`
*   `0 <= k <= 109`
*/

class Solution {
    public int maxDistinctElements(int[] arr, int diff) {
        if (arr.length == 0) return 0;
        Arrays.sort(arr);
        int count = 0;
        int prev = Integer.MIN_VALUE >> 1;
        for (int i = 0, n = arr.length; i < n; i++) {
            int a = arr[i];
            int low = a - diff;
            int high = a + diff;
            int x = prev + 1;
            if (x < low) x = low;
            if (x <= high) {
                count++;
                prev = x;
            }
        }
        return count;
    }
}
