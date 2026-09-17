/*
2435. Paths in Matrix Whose Sum Is Divisible by K   [Hard]
https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/

Runtime: 30 ms   Memory: 82.5 MB

You are given a **0-indexed** `m x n` integer matrix `grid` and an integer `k`. You are currently at position `(0, 0)` and you want to reach position `(m - 1, n - 1)` moving only **down** or **right**.

Return _the number of paths where the sum of the elements on the path is divisible by_ `k`. Since the answer may be very large, return it **modulo** `109 + 7`.

**Example 1:**

![](https://assets.leetcode.com/uploads/2022/08/13/image-20220813183124-1.png)

**Input:** grid = \[\[5,2,4\],\[3,0,5\],\[0,7,2\]\], k = 3
**Output:** 2
**Explanation:** There are two paths where the sum of the elements on the path is divisible by k.
The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.

**Example 2:**

![](https://assets.leetcode.com/uploads/2022/08/17/image-20220817112930-3.png)

**Input:** grid = \[\[0,0\]\], k = 5
**Output:** 1
**Explanation:** The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.

**Example 3:**

![](https://assets.leetcode.com/uploads/2022/08/12/image-20220812224605-3.png)

**Input:** grid = \[\[7,3,4,9\],\[2,3,6,2\],\[2,3,7,0\]\], k = 1
**Output:** 10
**Explanation:** Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.

**Constraints:**

*   `m == grid.length`
*   `n == grid[i].length`
*   `1 <= m, n <= 5 * 104`
*   `1 <= m * n <= 5 * 104`
*   `0 <= grid[i][j] <= 100`
*   `1 <= k <= 50`
*/

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] prev = new int[n][k];
        int[][] curr = new int[n][k];

        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum = (sum + grid[0][j]) % k;
            prev[j][sum] = 1;
        }

        sum = grid[0][0] % k;

        for (int i = 1; i < m; i++) {
            sum = (sum + grid[i][0]) % k;
            Arrays.fill(curr[0], 0);
            curr[0][sum] = 1;

            for (int j = 1; j < n; j++) {
                Arrays.fill(curr[j], 0);
                int val = grid[i][j];

                for (int r = 0; r < k; r++) {
                    int nr = (r + val) % k;
                    curr[j][nr] = (prev[j][r] + curr[j - 1][r]) % MOD;
                }
            }

            int[][] tmp = prev;
            prev = curr;
            curr = tmp;
        }

        return prev[n - 1][0];
    }
}
