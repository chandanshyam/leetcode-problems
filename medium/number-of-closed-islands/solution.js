/*
1254. Number of Closed Islands   [Medium]
https://leetcode.com/problems/number-of-closed-islands/

Runtime: 3 ms   Memory: 55.9 MB

Given a 2D `grid` consists of `0s` (land) and `1s` (water).  An _island_ is a maximal 4-directionally connected group of `0s` and a _closed island_ is an island **totally** (all left, top, right, bottom) surrounded by `1s.`

Return the number of _closed islands_.

**Example 1:**

![](https://assets.leetcode.com/uploads/2019/10/31/sample_3_1610.png)

**Input:** grid = \[\[1,1,1,1,1,1,1,0\],\[1,0,0,0,0,1,1,0\],\[1,0,1,0,1,1,1,0\],\[1,0,0,0,0,1,0,1\],\[1,1,1,1,1,1,1,0\]\]
**Output:** 2
**Explanation:** 
Islands in gray are closed because they are completely surrounded by water (group of 1s).

**Example 2:**

![](https://assets.leetcode.com/uploads/2019/10/31/sample_4_1610.png)

**Input:** grid = \[\[0,0,1,0,0\],\[0,1,0,1,0\],\[0,1,1,1,0\]\]
**Output:** 1

**Example 3:**

**Input:** grid = \[\[1,1,1,1,1,1,1\],
               \[1,0,0,0,0,0,1\],
               \[1,0,1,1,1,0,1\],
               \[1,0,1,0,1,0,1\],
               \[1,0,1,1,1,0,1\],
               \[1,0,0,0,0,0,1\],
               \[1,1,1,1,1,1,1\]\]
**Output:** 2

**Constraints:**

*   `1 <= grid.length, grid[0].length <= 100`
*   `0 <= grid[i][j] <=1`
*/

/**
 * @param {number[][]} grid
 * @return {number}
 */
var closedIsland = function(grid) {
    const m = grid.length, n = grid[0].length;
    const closed = (i, j) => {  // flood the island through (i, j); 1 if it never reaches the border
        if (i < 0 || i >= m || j < 0 || j >= n) return 0;  // stepped off the grid: the island touches the border
        if (grid[i][j]) return 1;  // water, or land we already flooded
        grid[i][j] = 1;  // visited: turn it into water
        return closed(i + 1, j) & closed(i - 1, j) & closed(i, j + 1) & closed(i, j - 1);  // & (not &&) floods all 4 sides
    };
    let count = 0;
    for (let i = 0; i < m; i++)
        for (let j = 0; j < n; j++)
            if (!grid[i][j]) count += closed(i, j);
    return count;
};
