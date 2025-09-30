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

