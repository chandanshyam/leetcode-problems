class Solution {
    public int snakesAndLadders(int[][] board) {
        
        int n = board.length;
        int target = n * n;
        
        
       Queue<int[]> deque = new LinkedList<>();
        boolean[] Visited = new boolean[target+1];
        
       deque.offer(new int[]{1, 0});
        
        Visited[1]=true;
        
        while(!deque.isEmpty())
        {
            int[] curr = deque.poll();
            int square = curr[0];
            int moves = curr[1];
            
            
            if(square == target)
                {
                    return moves;
                }
            
            
            for(int dice=1;dice<=6;dice++)
            {
                int nextSquare = dice + square;
                
                
                
                if (nextSquare > target) continue;
                
                int[] coord = getCoordinates(nextSquare, n);
                
                int r=coord[0];
                int c= coord[1];
                
                if(board[r][c]!=-1)
                {
                  nextSquare = board[r][c];
                }
                
                if(!Visited[nextSquare])
                {
                    Visited[nextSquare] = true;
                    deque.offer(new int[]{nextSquare, moves+1});
                }
            }
            
        }
        
        return -1;  
        
    }
    
    public int[] getCoordinates(int square, int n)
    {
        
        int row = n - 1 - (square-1)/n;
        int col = (square-1)%n;
        
        if((n-row)%2==0)
        {
            col = n - 1   - col;
        }
        
        return new int[]{row, col};
        
        
    }
}
