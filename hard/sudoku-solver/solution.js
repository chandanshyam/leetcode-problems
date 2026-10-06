/*
37. Sudoku Solver   [Hard]
https://leetcode.com/problems/sudoku-solver/

Runtime: 1190 ms   Memory: 78 MB

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy **all of the following rules**:

1.  Each of the digits `1-9` must occur exactly once in each row.
2.  Each of the digits `1-9` must occur exactly once in each column.
3.  Each of the digits `1-9` must occur exactly once in each of the 9 `3x3` sub-boxes of the grid.

The `'.'` character indicates empty cells.

**Example 1:**

![](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)

**Input:** board = \[\["5","3",".",".","7",".",".",".","."\],\["6",".",".","1","9","5",".",".","."\],\[".","9","8",".",".",".",".","6","."\],\["8",".",".",".","6",".",".",".","3"\],\["4",".",".","8",".","3",".",".","1"\],\["7",".",".",".","2",".",".",".","6"\],\[".","6",".",".",".",".","2","8","."\],\[".",".",".","4","1","9",".",".","5"\],\[".",".",".",".","8",".",".","7","9"\]\]
**Output:** \[\["5","3","4","6","7","8","9","1","2"\],\["6","7","2","1","9","5","3","4","8"\],\["1","9","8","3","4","2","5","6","7"\],\["8","5","9","7","6","1","4","2","3"\],\["4","2","6","8","5","3","7","9","1"\],\["7","1","3","9","2","4","8","5","6"\],\["9","6","1","5","3","7","2","8","4"\],\["2","8","7","4","1","9","6","3","5"\],\["3","4","5","2","8","6","1","7","9"\]\]
**Explanation:** The input board is shown above and the only valid solution is shown below:

![](https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png)

**Constraints:**

*   `board.length == 9`
*   `board[i].length == 9`
*   `board[i][j]` is a digit or `'.'`.
*   It is **guaranteed** that the input board has only one solution.
*/

/**
 * @param {character[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */
var solveSudoku = function(board) {
    
 let N = 9;
    let rows = Array.from({ length: N }, () => new Map());
    let columns = Array.from({ length: N }, () => new Map());
    let boxes = Array.from({ length: N }, () => new Map());
    let sudoku_solved = false;
    function box_index(row, col) {
        return Math.floor(row / 3) * 3 + Math.floor(col / 3);
    }
function could_place(d, row, col) {
        let res = !(
            rows[row].has(String(d)) ||
            columns[col].has(String(d)) ||
            boxes[box_index(row, col)].has(String(d))
        );
        return res;
    }
    function place_number(d, row, col) {
        rows[row].set(
            String(d),
            rows[row].has(String(d)) ? rows[row].get(String(d)) + 1 : 1,
        );
        columns[col].set(
            String(d),
            columns[col].has(String(d)) ? columns[col].get(String(d)) + 1 : 1,
        );
        boxes[box_index(row, col)].set(
            String(d),
            boxes[box_index(row, col)].has(String(d))
                ? boxes[box_index(row, col)].get(String(d)) + 1
                : 1,
        );
        board[row][col] = String(d);
    }
    function remove_number(d, row, col) {
        rows[row].delete(String(d), rows[row].get(String(d)) - 1);
        columns[col].delete(String(d), columns[col].get(String(d)) - 1);
        boxes[box_index(row, col)].delete(
            String(d),
            boxes[box_index(row, col)].get(String(d)) - 1,
        );
        board[row][col] = ".";
    }
    function place_next_numbers(row, col) {
        if (col === N - 1 && row === N - 1) sudoku_solved = true;
        else {
            if (col === N - 1) backtrack(row + 1, 0);
            else backtrack(row, col + 1);
        }
    }
    function backtrack(row = 0, col = 0) {
        if (board[row][col] === ".") {
            for (let d = 1; d < 10; d++) {
                if (could_place(d, row, col)) {
                    place_number(d, row, col);
                    place_next_numbers(row, col);
                    if (!sudoku_solved) remove_number(d, row, col);
                }
            }
        } else place_next_numbers(row, col);
    }
    for (let i = 0; i < N; ++i)
        for (let j = 0; j < N; ++j)
            if (board[i][j] !== ".") place_number(parseInt(board[i][j]), i, j);
    backtrack();
};
