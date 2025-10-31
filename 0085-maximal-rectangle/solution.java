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
