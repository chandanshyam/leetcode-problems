/*
417. Pacific Atlantic Water Flow   [Medium]
https://leetcode.com/problems/pacific-atlantic-water-flow/

Runtime: 4 ms   Memory: 46.5 MB

There is an `m x n` rectangular island that borders both the **Pacific Ocean** and **Atlantic Ocean**. The **Pacific Ocean** touches the island's left and top edges, and the **Atlantic Ocean** touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an `m x n` integer matrix `heights` where `heights[r][c]` represents the **height above sea level** of the cell at coordinate `(r, c)`.

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is **less than or equal to** the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return _a **2D list** of grid coordinates_ `result` _where_ `result[i] = [ri, ci]` _denotes that rain water can flow from cell_ `(ri, ci)` _to **both** the Pacific and Atlantic oceans_.

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/06/08/waterflow-grid.jpg)

**Input:** heights = \[\[1,2,2,3,5\],\[3,2,3,4,4\],\[2,4,5,3,1\],\[6,7,1,4,5\],\[5,1,1,2,4\]\]
**Output:** \[\[0,4\],\[1,3\],\[1,4\],\[2,2\],\[3,0\],\[3,1\],\[4,0\]\]
**Explanation:** The following cells can flow to the Pacific and Atlantic oceans, as shown below:
\[0,4\]: \[0,4\] -> Pacific Ocean 
       \[0,4\] -> Atlantic Ocean
\[1,3\]: \[1,3\] -> \[0,3\] -> Pacific Ocean 
       \[1,3\] -> \[1,4\] -> Atlantic Ocean
\[1,4\]: \[1,4\] -> \[1,3\] -> \[0,3\] -> Pacific Ocean 
       \[1,4\] -> Atlantic Ocean
\[2,2\]: \[2,2\] -> \[1,2\] -> \[0,2\] -> Pacific Ocean 
       \[2,2\] -> \[2,3\] -> \[2,4\] -> Atlantic Ocean
\[3,0\]: \[3,0\] -> Pacific Ocean 
       \[3,0\] -> \[4,0\] -> Atlantic Ocean
\[3,1\]: \[3,1\] -> \[3,0\] -> Pacific Ocean 
       \[3,1\] -> \[4,1\] -> Atlantic Ocean
\[4,0\]: \[4,0\] -> Pacific Ocean 
       \[4,0\] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.

**Example 2:**

**Input:** heights = \[\[1\]\]
**Output:** \[\[0,0\]\]
**Explanation:** The water can flow from the only cell to the Pacific and Atlantic oceans.

**Constraints:**

*   `m == heights.length`
*   `n == heights[r].length`
*   `1 <= m, n <= 200`
*   `0 <= heights[r][c] <= 105`
*/

class Solution {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        
        boolean[][] pac = new boolean[rows][cols];
        boolean[][] atl = new boolean[rows][cols];

        List<List<Integer>> res = new ArrayList<>();

        
        for(int c=0;c<cols;c++)
        {
            dfs(pac, heights, 0, c);
            dfs(atl, heights, rows - 1, c);

        }

         for(int r=0;r<rows; r++)
        {
             dfs( pac, heights, r, 0);
            dfs(atl, heights, r, cols - 1);

            
        }

        for(int r=0;r<rows;r++)
        {
            for(int c=0; c< cols; c++)
            {
                if(pac[r][c] && atl[r][c])
                {

                    res.add(Arrays.asList(r, c));

                }
            }
        }
        return res;
    }

    private void dfs(boolean[][] ocean,int[][] height, int r, int c)
    {
         int rows = ocean.length;
        int cols = ocean[0].length; 

        ocean[r][c] = true;
        for(int[] dir: dirs)
        {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(nr >=0 && nc >= 0 && nr < rows && nc < cols &&
                !ocean[nr][nc] && height[nr][nc] >= height[r][c])
                {
                    dfs(ocean, height, nr, nc);
                }
        }

    }
}
