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
