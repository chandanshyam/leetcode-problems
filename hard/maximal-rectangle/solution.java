/*
85. Maximal Rectangle   [Hard]
https://leetcode.com/problems/maximal-rectangle/

Runtime: 4 ms   Memory: 49.6 MB

Given a `rows x cols` binary `matrix` filled with `0`'s and `1`'s, find the largest rectangle containing only `1`'s and return _its area_.

**Example 1:**

![](https://assets.leetcode.com/uploads/2020/09/14/maximal.jpg)

**Input:** matrix = \[\["1","0","1","0","0"\],\["1","0","1","1","1"\],\["1","1","1","1","1"\],\["1","0","0","1","0"\]\]
**Output:** 6
**Explanation:** The maximal rectangle is shown in the above picture.

**Example 2:**

**Input:** matrix = \[\["0"\]\]
**Output:** 0

**Example 3:**

**Input:** matrix = \[\["1"\]\]
**Output:** 1

**Constraints:**

*   `rows == matrix.length`
*   `cols == matrix[i].length`
*   `1 <= rows, cols <= 200`
*   `matrix[i][j]` is `'0'` or `'1'`.
*/

class Solution {
    public int maximalRectangle(char[][] matrix) {
        
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] height = new int[matrix[0].length];
        
        int result = 0;

        for(int i = 0; i < matrix.length; ++i) {
            for(int j = 0; j < matrix[0].length; ++j) {
                if(matrix[i][j] == '1') {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }
            result = Math.max(result, largestRectangleArea(height));
        }
        return result;
    }

    private int largestRectangleArea(int[] heights) {
        
        if(heights == null || heights.length == 0)
            return 0;
        int maxArea = 0;
        int n = heights.length;
        int[] lessFromLeft = new int[n];
        int[] lessFromRight = new int[n];
        lessFromLeft[0] = -1;
        lessFromRight[n - 1] = n;

        for(int i = 1; i < n; ++i) {
            int p = i - 1;
            while(p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for(int i = n - 2; i >= 0; --i) {
            int p = i + 1;
            while(p < n && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        for(int i = 0 ; i < n; ++i) {
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }
        return maxArea;
    }
}
