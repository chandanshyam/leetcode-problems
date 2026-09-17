/*
1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold   [Medium]
https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/

Runtime: 5 ms   Memory: 58.2 MB

Given a `m x n` matrix `mat` and an integer `threshold`, return _the maximum side-length of a square with a sum less than or equal to_ `threshold` _or return_ `0` _if there is no such square_.

**Example 1:**

![](https://assets.leetcode.com/uploads/2019/12/05/e1.png)

**Input:** mat = \[\[1,1,3,2,4,3,2\],\[1,1,3,2,4,3,2\],\[1,1,3,2,4,3,2\]\], threshold = 4
**Output:** 2
**Explanation:** The maximum side length of square with sum less than or equal to 4 is 2 as shown.

**Example 2:**

**Input:** mat = \[\[2,2,2,2,2\],\[2,2,2,2,2\],\[2,2,2,2,2\],\[2,2,2,2,2\],\[2,2,2,2,2\]\], threshold = 1
**Output:** 0

**Constraints:**

*   `m == mat.length`
*   `n == mat[i].length`
*   `1 <= m, n <= 300`
*   `0 <= mat[i][j] <= 104`
*   `0 <= threshold <= 105`
*/

class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        
        int[][] P = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                P[i][j] = mat[i-1][j-1] + P[i-1][j] + P[i][j-1] - P[i-1][j-1];
            }
        }
        
        int maxSide = 0;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int len = maxSide + 1;
                
                if (i >= len && j >= len) {
                    int r1 = i - len + 1;
                    int c1 = j - len + 1;
                    
                    // Calculate sum of square defined by (r1, c1) and (i, j)
                    int total = P[i][j] - P[r1-1][j] - P[i][c1-1] + P[r1-1][c1-1];
                    
                    if (total <= threshold) {
                        maxSide++;
                    }
                }
            }
        }
        
        return maxSide;
    }
}
