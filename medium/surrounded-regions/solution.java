/*
130. Surrounded Regions   [Medium]
https://leetcode.com/problems/surrounded-regions/

Runtime: 1 ms   Memory: 45.4 MB

You are given an `m x n` matrix `board` containing **letters** `'X'` and `'O'`, **capture regions** that are **surrounded**:

*   **Connect**: A cell is connected to adjacent cells horizontally or vertically.
*   **Region**: To form a region **connect every** `'O'` cell.
*   **Surround**: A region is surrounded if none of the `'O'` cells in that region are on the edge of the board. Such regions are **completely enclosed** by `'X'` cells.

To capture a **surrounded region**, replace all `'O'`s with `'X'`s **in-place** within the original board. You do not need to return anything.

**Example 1:**

**Input:** board = \[\["X","X","X","X"\],\["X","O","O","X"\],\["X","X","O","X"\],\["X","O","X","X"\]\]

**Output:** \[\["X","X","X","X"\],\["X","X","X","X"\],\["X","X","X","X"\],\["X","O","X","X"\]\]

**Explanation:**

![](https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg)

In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

**Example 2:**

**Input:** board = \[\["X"\]\]

**Output:** \[\["X"\]\]

**Constraints:**

*   `m == board.length`
*   `n == board[i].length`
*   `1 <= m, n <= 200`
*   `board[i][j]` is `'X'` or `'O'`.
*/

class Solution {
    public void solve(char[][] board) {
        
        int rows = board.length;
        int cols = board[0].length;

        for(int r=0;r<rows;r++)
        {
            if(board[r][0]=='O')
            {
                capture(board, r, 0);
            }
            if(board[r][cols-1]=='O')
            {
                capture(board, r, cols-1);
            }
        }

        for(int c=0;c<cols;c++)
        {
            if(board[0][c]=='O')
            {
                capture(board, 0, c);
            }
            if(board[rows-1][c]=='O')
            {
                capture(board, rows-1, c);
            }
        }

  for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'T') {
                    board[r][c] = 'O';
                }
            }
        }
    }


    public void capture(char[][] board, int r, int c)
    {
        int rows = board.length;
        int cols = board[0].length;

        if( r<0 || c<0 || r>= rows || c>= cols ||
            board[r][c] !='O')
            {
                return;
            }

            board[r][c] = 'T';
            capture(board, r+1, c);
            capture(board, r-1, c);
            capture(board, r, c+1);
            capture(board, r, c-1);
    }
}
