/*
1267. Count Servers that Communicate   [Medium]
https://leetcode.com/problems/count-servers-that-communicate/

Runtime: 37 ms   Memory: 65.3 MB

You are given a map of a server center, represented as a `m * n` integer matrix `grid`, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.  
  
Return the number of servers that communicate with any other server.

**Example 1:**

![](https://assets.leetcode.com/uploads/2019/11/14/untitled-diagram-6.jpg)

**Input:** grid = \[\[1,0\],\[0,1\]\]
**Output:** 0
**Explanation:** No servers can communicate with others.

**Example 2:**

**![](https://assets.leetcode.com/uploads/2019/11/13/untitled-diagram-4.jpg)**

**Input:** grid = \[\[1,0\],\[1,1\]\]
**Output:** 3
**Explanation:** All three servers can communicate with at least one other server.

**Example 3:**

![](https://assets.leetcode.com/uploads/2019/11/14/untitled-diagram-1-3.jpg)

**Input:** grid = \[\[1,1,0,0\],\[0,0,1,0\],\[0,0,1,0\],\[0,0,0,1\]\]
**Output:** 4
**Explanation:** The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.

**Constraints:**

*   `m == grid.length`
*   `n == grid[i].length`
*   `1 <= m <= 250`
*   `1 <= n <= 250`
*   `grid[i][j] == 0 or 1`
*/

/**
 * @param {number[][]} grid
 * @return {number}
 */
var countServers = function(grid) {
    const rows = grid.map(row => row.reduce((a, b) => a + b));  // servers on each row
    const cols = grid[0].map((_, j) => grid.reduce((sum, row) => sum + row[j], 0));  // servers on each column

    let ans = 0;
    for (const [i, row] of grid.entries())
        for (const [j, server] of row.entries())
            if (server && rows[i] + cols[j] > 2) ans++;  // it's counted in both, so more than 2 means company
    return ans;
};
