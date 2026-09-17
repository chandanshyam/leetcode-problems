/*
994. Rotting Oranges   [Medium]
https://leetcode.com/problems/rotting-oranges/

Runtime: 2 ms   Memory: 42.1 MB

You are given an `m x n` `grid` where each cell can have one of three values:

*   `0` representing an empty cell,
*   `1` representing a fresh orange, or
*   `2` representing a rotten orange.

Every minute, any fresh orange that is **4-directionally adjacent** to a rotten orange becomes rotten.

Return _the minimum number of minutes that must elapse until no cell has a fresh orange_. If _this is impossible, return_ `-1`.

**Example 1:**

![](https://assets.leetcode.com/uploads/2019/02/16/oranges.png)

**Input:** grid = \[\[2,1,1\],\[1,1,0\],\[0,1,1\]\]
**Output:** 4

**Example 2:**

**Input:** grid = \[\[2,1,1\],\[0,1,1\],\[1,0,1\]\]
**Output:** -1
**Explanation:** The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

**Example 3:**

**Input:** grid = \[\[0,2\]\]
**Output:** 0
**Explanation:** Since there are already no fresh oranges at minute 0, the answer is just 0.

**Constraints:**

*   `m == grid.length`
*   `n == grid[i].length`
*   `1 <= m, n <= 10`
*   `grid[i][j]` is `0`, `1`, or `2`.
*/

class Solution {
    public int orangesRotting(int[][] grid) {
        
        int fresh =0;
        int time =0;

        Queue<int[]> q = new ArrayDeque<>();
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dirs = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};

        for(int r=0;r< rows;r++)
        {
            for(int c=0;c<cols; c++)
            {
                if(grid[r][c]==1)
                {
                    fresh++;
                }

                if(grid[r][c]==2)
                {
                    q.offer(new int[]{r, c});
                }
            }
        }


        while(fresh > 0 && !q.isEmpty())
        {
            int l = q.size();

            for(int i=0; i<l ;i++)
            {
            int[] curr = q.poll();
            for(int[] dir : dirs)
            {
                int r = curr[0] + dir[0];
                int c = curr[1] + dir[1];
             if(r >= 0 && c >= 0 && r < rows && c < cols && grid[r][c] == 1)
            {
                grid[r][c] = 2;
                q.offer(new int[]{r, c});
                fresh--;
            }
                
            }   
            }
             time++;
           
        }

        return fresh == 0 ? time : -1;
    }
}
