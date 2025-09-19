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

