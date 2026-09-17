/*
4. Median of Two Sorted Arrays   [Hard]
https://leetcode.com/problems/median-of-two-sorted-arrays/

Runtime: 2 ms   Memory: 46.4 MB

Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return **the median** of the two sorted arrays.

The overall run time complexity should be `O(log (m+n))`.

**Example 1:**

**Input:** nums1 = \[1,3\], nums2 = \[2\]
**Output:** 2.00000
**Explanation:** merged array = \[1,2,3\] and median is 2.

**Example 2:**

**Input:** nums1 = \[1,2\], nums2 = \[3,4\]
**Output:** 2.50000
**Explanation:** merged array = \[1,2,3,4\] and median is (2 + 3) / 2 = 2.5.

**Constraints:**

*   `nums1.length == m`
*   `nums2.length == n`
*   `0 <= m <= 1000`
*   `0 <= n <= 1000`
*   `1 <= m + n <= 2000`
*   `-106 <= nums1[i], nums2[i] <= 106`
*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] A = nums1, B = nums2;
        int m = A.length, n = B.length;
        if (m > n) { // ensure A is shorter
            A = nums2; B = nums1;
            m = A.length; n = B.length;
        }

        int total = m + n;
        int half = (total + 1) / 2;

        int lo = 0, hi = m;
        while (lo <= hi) {
            int i = (lo + hi) / 2;
            int j = half - i;

            int Aleft  = (i > 0) ? A[i - 1] : Integer.MIN_VALUE;
            int Aright = (i < m) ? A[i]     : Integer.MAX_VALUE;
            int Bleft  = (j > 0) ? B[j - 1] : Integer.MIN_VALUE;
            int Bright = (j < n) ? B[j]     : Integer.MAX_VALUE;

            if (Aleft <= Bright && Bleft <= Aright) {
                if ((total & 1) == 1) {
                    return Math.max(Aleft, Bleft);
                }
                return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
            } else if (Aleft > Bright) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("Invalid input");
    }
}
