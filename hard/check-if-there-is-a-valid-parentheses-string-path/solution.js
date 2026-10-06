/*
2267.  Check if There Is a Valid Parentheses String Path   [Hard]
https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/

Runtime: 21 ms   Memory: 61.4 MB

A parentheses string is a **non-empty** string consisting only of `'('` and `')'`. It is **valid** if **any** of the following conditions is **true**:

*   It is `()`.
*   It can be written as `AB` (`A` concatenated with `B`), where `A` and `B` are valid parentheses strings.
*   It can be written as `(A)`, where `A` is a valid parentheses string.

You are given an `m x n` matrix of parentheses `grid`. A **valid parentheses string path** in the grid is a path satisfying **all** of the following conditions:

*   The path starts from the upper left cell `(0, 0)`.
*   The path ends at the bottom-right cell `(m - 1, n - 1)`.
*   The path only ever moves **down** or **right**.
*   The resulting parentheses string formed by the path is **valid**.

Return `true` _if there exists a **valid parentheses string path** in the grid._ Otherwise, return `false`.

**Example 1:**

![](https://assets.leetcode.com/uploads/2022/03/15/example1drawio.png)

**Input:** grid = \[\["(","(","("\],\[")","(",")"\],\["(","(",")"\],\["(","(",")"\]\]
**Output:** true
**Explanation:** The above diagram shows two possible paths that form valid parentheses strings.
The first path shown results in the valid parentheses string "()(())".
The second path shown results in the valid parentheses string "((()))".
Note that there may be other valid parentheses string paths.

**Example 2:**

![](https://assets.leetcode.com/uploads/2022/03/15/example2drawio.png)

**Input:** grid = \[\[")",")"\],\["(","("\]\]
**Output:** false
**Explanation:** The two possible paths form the parentheses strings "))(" and ")((". Since neither of them are valid parentheses strings, we return false.

**Constraints:**

*   `m == grid.length`
*   `n == grid[i].length`
*   `1 <= m, n <= 100`
*   `grid[i][j]` is either `'('` or `')'`.
*/

/**
 * @param {character[][]} grid
 * @return {boolean}
 */
const hasValidPath = A => {
    const m = A.length, n = A[0].length;

    if (~(m + n) & 1 || A[0][0].charCodeAt() & 1 || ~A.at(-1).at(-1).charCodeAt() & 1)
        return 0;

    const dfs = _.memoize(
        (i, j, x) => {
            x += 1 - ((A[i][j].charCodeAt() & 1) << 1);

            if (x < 0 || x > m - i + n - j - 1)
                return 0;

            if (i === m - 1 && j === n - 1)
                return x === 0;

            return (i < m - 1 && dfs(i + 1, j, x)) || (j < n - 1 && dfs(i, j + 1, x));
        },
        (i, j, x) => `${i},${j},${x}`
    );

    return !!dfs(0, 0, 0);
};
