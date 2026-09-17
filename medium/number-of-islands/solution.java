/*
200. Number of Islands   [Medium]
https://leetcode.com/problems/number-of-islands/

Runtime: 3 ms   Memory: 49.5 MB

Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return _the number of islands_.

An **island** is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

**Example 1:**

**Input:** grid = \[
  \["1","1","1","1","0"\],
  \["1","1","0","1","0"\],
  \["1","1","0","0","0"\],
  \["0","0","0","0","0"\]
\]
**Output:** 1

**Example 2:**

**Input:** grid = \[
  \["1","1","0","0","0"\],
  \["1","1","0","0","0"\],
  \["0","0","1","0","0"\],
  \["0","0","0","1","1"\]
\]
**Output:** 3

**Constraints:**

*   `m == grid.length`
*   `n == grid[i].length`
*   `1 <= m, n <= 300`
*   `grid[i][j]` is `'0'` or `'1'`.
*/

class Solution {

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0,-1}};

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0]. length;

        int islands =0;

        for(int r=0;r<rows; r++)
        {
            for(int c =0; c< cols; c++)
            {
                if(grid[r][c] == '1')
                {
                dfs(grid, r, c);
                islands++;}
            }
        }

        return islands;
    }


    public void dfs(char[][] gr, int r, int c)
    {


        int rows = gr.length;
        int cols = gr[0]. length;

        if( r<0 || c<0 || r>= rows || c>= cols || gr[r][c] == '0')
        {
            return;
        }


        gr[r][c] = '0';

        for(int[] dir : dirs)
        {
            dfs(gr, r + dir[0], c + dir[1] );

        }

    }
}
