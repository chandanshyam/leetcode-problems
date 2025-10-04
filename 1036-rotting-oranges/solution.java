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
