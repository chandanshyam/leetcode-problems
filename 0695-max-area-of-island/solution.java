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

