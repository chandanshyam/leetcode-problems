/*
463. Island Perimeter   [Easy]
https://leetcode.com/problems/island-perimeter/

Runtime: 8 ms   Memory: 46.1 MB

You are given `row x col` `grid` representing a map where `grid[i][j] = 1` represents land and `grid[i][j] = 0` represents water.

Grid cells are connected **horizontally/vertically** (not diagonally). The `grid` is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

**Example 1:**

![](https://assets.leetcode.com/uploads/2018/10/12/island.png)

**Input:** grid = \[\[0,1,0,0\],\[1,1,1,0\],\[0,1,0,0\],\[1,1,0,0\]\]
**Output:** 16
**Explanation:** The perimeter is the 16 yellow stripes in the image above.

**Example 2:**

**Input:** grid = \[\[1\]\]
**Output:** 4

**Example 3:**

**Input:** grid = \[\[1,0\]\]
**Output:** 4

**Constraints:**

*   `row == grid.length`
*   `col == grid[i].length`
*   `1 <= row, col <= 100`
*   `grid[i][j]` is `0` or `1`.
*   There is exactly one island in `grid`.
*/

class Solution {
    private int[][] grid;
    private boolean[][] visited;
    private int rows;
    private int cols;
    
    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];
        
        for(int r=0;r<rows;r++)
        {
            for(int c=0;c<cols;c++)
            {
                if(grid[r][c]==1)
                {
                    return dfs(r, c);
                }
            }
        }
        return 0;
    }

     public int dfs(int i, int j)
    {
        if(i<0 || j<0 || i>=rows || j >= cols
        || grid[i][j] ==0) return 1;

        if(visited[i][j] == true) return 0;

        visited[i][j] = true;

        return dfs(i+1, j) + dfs(i-1, j) + dfs(i, j+1) + dfs(i, j-1);
    }
  }
