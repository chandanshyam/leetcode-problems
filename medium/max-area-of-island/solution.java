/*
695. Max Area of Island   [Medium]
https://leetcode.com/problems/max-area-of-island/

Runtime: 2 ms   Memory: 44.3 MB

You are given an `m x n` binary matrix `grid`. An island is a group of `1`'s (representing land) connected **4-directionally** (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The **area** of an island is the number of cells with a value `1` in the island.

Return _the maximum **area** of an island in_ `grid`. If there is no island, return `0`.

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg)

**Input:** grid = \[\[0,0,1,0,0,0,0,1,0,0,0,0,0\],\[0,0,0,0,0,0,0,1,1,1,0,0,0\],\[0,1,1,0,1,0,0,0,0,0,0,0,0\],\[0,1,0,0,1,1,0,0,1,0,1,0,0\],\[0,1,0,0,1,1,0,0,1,1,1,0,0\],\[0,0,0,0,0,0,0,0,0,0,1,0,0\],\[0,0,0,0,0,0,0,1,1,1,0,0,0\],\[0,0,0,0,0,0,0,1,1,0,0,0,0\]\]
**Output:** 6
**Explanation:** The answer is not 11, because the island must be connected 4-directionally.

**Example 2:**

**Input:** grid = \[\[0,0,0,0,0,0,0,0\]\]
**Output:** 0

**Constraints:**

*   `m == grid.length`
*   `n == grid[i].length`
*   `1 <= m, n <= 50`
*   `grid[i][j]` is either `0` or `1`.
*/

class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        int area = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for(int r=0;r<rows; r++)
        {
            for(int c=0;c< cols; c++)
            {
                if(grid[r][c] == 1)
                {
                     area = Math.max(area, dfs(grid, r, c));
                }
            }
        }
       return area;
    }

    public int dfs(int[][] grid, int r, int c)
    {
        //in the dfs function you check if the 
        int rows = grid.length;
        int cols = grid[0].length;
        if(r < 0 || c<0 || r>= rows || c>= cols ||
        grid[r][c] == 0)
        {
            return 0;
        }


        grid[r][c] = 0;
        int res=1;

        for(int[] dir: dirs)
        {
           res += dfs(grid, r + dir[0], dir[1] +  c);
        }

        return res;

    }
}
