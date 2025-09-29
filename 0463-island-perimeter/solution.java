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
