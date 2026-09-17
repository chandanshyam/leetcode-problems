/*
289. Game of Life   [Medium]
https://leetcode.com/problems/game-of-life/

Runtime: 2 ms   Memory: 57.4 MB

According to [Wikipedia's article](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life): "The **Game of Life**, also known simply as **Life**, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an `m x n` grid of cells, where each cell has an initial state: **live** (represented by a `1`) or **dead** (represented by a `0`). Each cell interacts with its [eight neighbors](https://en.wikipedia.org/wiki/Moore_neighborhood) (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

1.  Any live cell with fewer than two live neighbors dies as if caused by under-population.
2.  Any live cell with two or three live neighbors lives on to the next generation.
3.  Any live cell with more than three live neighbors dies, as if by over-population.
4.  Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The next state of the board is determined by applying the above rules simultaneously to every cell in the current state of the `m x n` grid `board`. In this process, births and deaths occur **simultaneously**.

Given the current state of the `board`, **update** the `board` to reflect its next state.

**Note** that you do not need to return anything.

**Example 1:**

![](https://assets.leetcode.com/uploads/2020/12/26/grid1.jpg)

**Input:** board = \[\[0,1,0\],\[0,0,1\],\[1,1,1\],\[0,0,0\]\]
**Output:** \[\[0,0,0\],\[1,0,1\],\[0,1,1\],\[0,1,0\]\]

**Example 2:**

![](https://assets.leetcode.com/uploads/2020/12/26/grid2.jpg)

**Input:** board = \[\[1,1\],\[1,0\]\]
**Output:** \[\[1,1\],\[1,1\]\]

**Constraints:**

*   `m == board.length`
*   `n == board[i].length`
*   `1 <= m, n <= 25`
*   `board[i][j]` is `0` or `1`.

**Follow up:**

*   Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
*   In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
*/

/**
 * @param {number[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */
var gameOfLife = function(board) {
    const n = board.length;
    const m = board[0].length;

    // Directions for neighbors
    const directions = [
        [-1, -1], [-1, 0], [-1, 1],
        [0, -1], [0, 1],
        [1, -1], [1, 0], [1, 1]
    ];

    // First pass: **Mark** temporary state changes
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            let liveNeighbors = 0;
            for (const [dx, dy] of directions) {
                const ni = i + dx;
                const nj = j + dy;

                if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                    // Check if neighbor was originally live
                    if (board[ni][nj] === 1 || board[ni][nj] === 2) {
                        liveNeighbors++;
                    }
                }
            }

            if (board[i][j] === 1) { // Current cell is live
                if (liveNeighbors < 2 || liveNeighbors > 3) {
                    board[i][j] = 2; // live -> dead
                }
            } else { // Current cell is dead
                if (liveNeighbors === 3) {
                    board[i][j] = 3; // dead -> live
                }
            }
        }
    }

    // Second pass: **Finalize** state changes
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (board[i][j] === 2) {
                board[i][j] = 0; // Finalize live -> dead
            } else if (board[i][j] === 3) {
                board[i][j] = 1; // Finalize dead -> live
            }
        }
    }
};
