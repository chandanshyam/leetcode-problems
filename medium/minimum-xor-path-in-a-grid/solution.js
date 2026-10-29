/*
3882. Minimum XOR Path in a Grid   [Medium]
https://leetcode.com/problems/minimum-xor-path-in-a-grid/

Runtime: 149 ms   Memory: 74.4 MB

You are given a 2D integer array `grid` of size `m * n`.

You start at the **top-left** cell `(0, 0)` and want to reach the **bottom-right** cell `(m - 1, n - 1)`.

At each step, you **may** move either **right or down**.

The **cost** of a path is defined as the **bitwise XOR** of all the values in the cells along that path, **including** the start and end cells.

Return the **minimum** possible XOR value among all valid paths from `(0, 0)` to `(m - 1, n - 1)`.

**Example 1:**

**Input:** grid = \[\[1,2\],\[3,4\]\]

**Output:** 6

**Explanation:**

There are two valid paths:

*   `(0, 0) → (0, 1) → (1, 1)` with XOR: `1 XOR 2 XOR 4 = 7`
*   `(0, 0) → (1, 0) → (1, 1)` with XOR: `1 XOR 3 XOR 4 = 6`

The minimum XOR value among all valid paths is 6.

**Example 2:**

**Input:** grid = \[\[6,7\],\[5,8\]\]

**Output:** 9

**Explanation:**

There are two valid paths:

*   `(0, 0) → (0, 1) → (1, 1)` with XOR: `6 XOR 7 XOR 8 = 9`
*   `(0, 0) → (1, 0) → (1, 1)` with XOR: `6 XOR 5 XOR 8 = 11`

The minimum XOR value among all valid paths is 9.

**Example 3:**

**Input:** grid = \[\[2,7,5\]\]

**Output:** 0

**Explanation:**

There is only one valid path:

*   `(0, 0) → (0, 1) → (0, 2)` with XOR: `2 XOR 7 XOR 5 = 0`

The XOR value of this path is 0, which is the minimum possible.

**Constraints:**

*   `1 <= m == grid.length <= 1000`
*   `1 <= n == grid[i].length <= 1000`
*   `m * n <= 1000`
*   `0 <= grid[i][j] <= 1023​`
*/

/**
 * @param {number[][]} grid
 * @return {number}
 */
var minCost = function (grid) {
    const m = grid.length;
    const n = grid[0].length;

    const dp = Array.from({ length: n }, () => new Uint8Array(1024));
    const next = new Uint8Array(1024);

    dp[0][grid[0][0]] = 1;

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (i === 0 && j === 0) {
                continue;
            }

            const value = grid[i][j];
            next.fill(0);

            for (let k = 0; k < 1024; k++) {
                if (dp[j][k] || (j > 0 && dp[j - 1][k])) {
                    next[k ^ value] = 1;
                }
            }

            dp[j].set(next);
        }
    }

    return dp[n - 1].indexOf(1);
};
