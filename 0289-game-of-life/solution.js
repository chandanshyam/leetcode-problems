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
