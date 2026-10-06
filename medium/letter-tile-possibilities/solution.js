/*
1079. Letter Tile Possibilities   [Medium]
https://leetcode.com/problems/letter-tile-possibilities/

Runtime: 6 ms   Memory: 54.3 MB

You have `n`  `tiles`, where each tile has one letter `tiles[i]` printed on it.

Return _the number of possible non-empty sequences of letters_ you can make using the letters printed on those `tiles`.

**Example 1:**

**Input:** tiles = "AAB"
**Output:** 8
**Explanation:** The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

**Example 2:**

**Input:** tiles = "AAABBC"
**Output:** 188

**Example 3:**

**Input:** tiles = "V"
**Output:** 1

**Constraints:**

*   `1 <= tiles.length <= 7`
*   `tiles` consists of uppercase English letters.
*/

/**
 * @param {string} tiles
 * @return {number}
 */
var numTilePossibilities = function(tiles) {
    tiles = tiles.split("").sort().join("");
    let used = new Array(tiles.length).fill(false);
    
    const backtrack = () => {
        let count = 0;
        for (let i = 0; i < tiles.length; i++) {
            if (used[i] || (i > 0 && tiles[i] === tiles[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            count += 1 + backtrack();
            used[i] = false;
        }
        return count;
    };

    return backtrack();
};
