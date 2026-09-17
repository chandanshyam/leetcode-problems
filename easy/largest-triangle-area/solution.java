/*
812. Largest Triangle Area   [Easy]
https://leetcode.com/problems/largest-triangle-area/

Runtime: 5 ms   Memory: 41.8 MB

Given an array of points on the **X-Y** plane `points` where `points[i] = [xi, yi]`, return _the area of the largest triangle that can be formed by any three different points_. Answers within `10-5` of the actual answer will be accepted.

**Example 1:**

![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/04/1027.png)

**Input:** points = \[\[0,0\],\[0,1\],\[1,0\],\[0,2\],\[2,0\]\]
**Output:** 2.00000
**Explanation:** The five points are shown in the above figure. The red triangle is the largest.

**Example 2:**

**Input:** points = \[\[1,0\],\[0,0\],\[0,1\]\]
**Output:** 0.50000

**Constraints:**

*   `3 <= points.length <= 50`
*   `-50 <= xi, yi <= 50`
*   All the given points are **unique**.
*/

class Solution {
    public double largestTriangleArea(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                for (int k = j+1; k < N; ++k)
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
        return ans;
    }

    public double area(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                             -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }
}
