/*
3625. Count Number of Trapezoids II   [Hard]
https://leetcode.com/problems/count-number-of-trapezoids-ii/

Runtime: 333 ms   Memory: 274.4 MB

You are given a 2D integer array `points` where `points[i] = [xi, yi]` represents the coordinates of the `ith` point on the Cartesian plane.

Return _the number of unique_ _trapezoids_ that can be formed by choosing any four distinct points from `points`.

A **trapezoid** is a convex quadrilateral with **at least one pair** of parallel sides. Two lines are parallel if and only if they have the same slope.

**Example 1:**

**Input:** points = \[\[-3,2\],\[3,0\],\[2,3\],\[3,2\],\[2,-3\]\]

**Output:** 2

**Explanation:**

![](https://assets.leetcode.com/uploads/2025/04/29/desmos-graph-4.png) ![](https://assets.leetcode.com/uploads/2025/04/29/desmos-graph-3.png)

There are two distinct ways to pick four points that form a trapezoid:

*   The points `[-3,2], [2,3], [3,2], [2,-3]` form one trapezoid.
*   The points `[2,3], [3,2], [3,0], [2,-3]` form another trapezoid.

**Example 2:**

**Input:** points = \[\[0,0\],\[1,0\],\[0,1\],\[2,1\]\]

**Output:** 1

**Explanation:**

![](https://assets.leetcode.com/uploads/2025/04/29/desmos-graph-5.png)

There is only one trapezoid which can be formed.

**Constraints:**

*   `4 <= points.length <= 500`
*   `–1000 <= xi, yi <= 1000`
*   All points are pairwise distinct.
*/

class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        double inf = 1e9 + 7;
        Map<Double , List<Double>> slopeToIntercept = new HashMap<>();
        Map<Integer , List<Double>> midToSlope = new HashMap<>();
        int res = 0;

        // Step 1: compute slopes and intercepts for all pairs...
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dx = x1 - x2;
                int dy = y1 - y2;

                double k;
                double b;

                if (x2 == x1) {
                    k = inf; // vertical line slope...
                    b = x1; // x-intercept...
                }
                else {
                    k = (1.0 * (y2 - y1)) / (x2 - x1);
                    b = (1.0 * (y1 * dx - x1 * dy)) / dx;
                }
                if (k == -0.0) k = 0.0;
                
                if (b == -0.0) b = 0.0;
                
                int mid = (x1 + x2) * 10000 + (y1 + y2);
                slopeToIntercept
                    .computeIfAbsent(k , key -> new ArrayList<>())
                    .add(b);
                midToSlope
                    .computeIfAbsent(mid , key -> new ArrayList<>())
                    .add(k);
            }
        }

        // Step 2: count trapezoids by slope groups...
        for (List<Double> sti : slopeToIntercept.values()) {
            if (sti.size() == 1) {
                continue;
            }

            Map<Double , Integer> count = new TreeMap<>();
            for (double b : sti) {
                count.put(b , count.getOrDefault(b , 0) + 1);
            }

            int sum = 0;
            for (int cnt : count.values()) {
                res += sum * cnt;
                sum += cnt;
            }
        }

        // Step 3: subtract duplicates by midpoint groups...
        for (List<Double> mts : midToSlope.values()) {
            if (mts.size() == 1) {
                continue;
            }
             
            Map<Double , Integer> count = new TreeMap<>();
            for (double k : mts) {
                count.put(k , count.getOrDefault(k , 0) + 1);
            }
            int sum = 0;
            for (int cnt : count.values()) {
                res -= sum * cnt;
                sum += cnt;
            }
        }

        return res;
    }
}
