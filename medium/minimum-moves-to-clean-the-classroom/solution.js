/*
3568. Minimum Moves to Clean the Classroom   [Medium]
https://leetcode.com/problems/minimum-moves-to-clean-the-classroom/

Runtime: 370 ms   Memory: 118.4 MB

You are given an `m x n` grid `classroom` where a student volunteer is tasked with cleaning up litter scattered around the room. Each cell in the grid is one of the following:

*   `'S'`: Starting position of the student
*   `'L'`: Litter that must be collected (once collected, the cell becomes empty)
*   `'R'`: Reset area that restores the student's energy to full capacity, regardless of their current energy level (can be used multiple times)
*   `'X'`: Obstacle the student cannot pass through
*   `'.'`: Empty space

You are also given an integer `energy`, representing the student's maximum energy capacity. The student starts with this energy from the starting position `'S'`.

Each move to an adjacent cell (up, down, left, or right) costs 1 unit of energy. If the energy reaches 0, the student can only continue if they are on a reset area `'R'`, which resets the energy to its **maximum** capacity `energy`.

Return the **minimum** number of moves required to collect all litter items, or `-1` if it's impossible.

**Example 1:**

**Input:** classroom = \["S.", "XL"\], energy = 2

**Output:** 2

**Explanation:**

*   The student starts at cell `(0, 0)` with 2 units of energy.
*   Since cell `(1, 0)` contains an obstacle 'X', the student cannot move directly downward.
*   A valid sequence of moves to collect all litter is as follows:
    *   Move 1: From `(0, 0)` → `(0, 1)` with 1 unit of energy and 1 unit remaining.
    *   Move 2: From `(0, 1)` → `(1, 1)` to collect the litter `'L'`.
*   The student collects all the litter using 2 moves. Thus, the output is 2.

**Example 2:**

**Input:** classroom = \["LS", "RL"\], energy = 4

**Output:** 3

**Explanation:**

*   The student starts at cell `(0, 1)` with 4 units of energy.
*   A valid sequence of moves to collect all litter is as follows:
    *   Move 1: From `(0, 1)` → `(0, 0)` to collect the first litter `'L'` with 1 unit of energy used and 3 units remaining.
    *   Move 2: From `(0, 0)` → `(1, 0)` to `'R'` to reset and restore energy back to 4.
    *   Move 3: From `(1, 0)` → `(1, 1)` to collect the second litter `'L'`.
*   The student collects all the litter using 3 moves. Thus, the output is 3.

**Example 3:**

**Input:** classroom = \["L.S", "RXL"\], energy = 3

**Output:** \-1

**Explanation:**

No valid path collects all `'L'`.

**Constraints:**

*   `1 <= m == classroom.length <= 20`
*   `1 <= n == classroom[i].length <= 20`
*   `classroom[i][j]` is one of `'S'`, `'L'`, `'R'`, `'X'`, or `'.'`
*   `1 <= energy <= 50`
*   There is exactly **one** `'S'` in the grid.
*   There are **at most** 10 `'L'` cells in the grid.
*/

/**
 * @param {string[]} classroom
 * @param {number} energy
 * @return {number}
 */
function minMoves(classroom, energy) {
    const dx = [0, 1, 0, -1];
    const dy = [1, 0, -1, 0];
    const m = classroom.length;
    const n = classroom[0].length;
    const id = Array.from({ length: m }, () => Array(n).fill(0));
    let sx = 0,
        sy = 0,
        cnt = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const c = classroom[i][j];
            if (c === "S") {
                sx = i;
                sy = j;
            } else if (c === "L") {
                id[i][j] = 1 << cnt;
                cnt++;
            }
        }
    }
    const full = 1 << cnt;
    const bestEnergy = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(full).fill(-1)),
    );
    bestEnergy[sx][sy][0] = energy;
    const q = [];
    q.push({ x: sx, y: sy, mask: 0, e: energy, steps: 0 });
    let head = 0;
    while (head < q.length) {
        const t = q[head++];
        if (t.mask === full - 1) {
            return t.steps;
        }
        if (t.e === 0) {
            continue;
        }
        for (let d = 0; d < 4; d++) {
            const nx = t.x + dx[d];
            const ny = t.y + dy[d];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            const c = classroom[nx][ny];
            if (c === "X") {
                continue;
            }
            const ne = c === "R" ? energy : t.e - 1;
            const nmask = t.mask | id[nx][ny];
            if (ne > bestEnergy[nx][ny][nmask]) {
                bestEnergy[nx][ny][nmask] = ne;
                q.push({
                    x: nx,
                    y: ny,
                    mask: nmask,
                    e: ne,
                    steps: t.steps + 1,
                });
            }
        }
    }
    return -1;
}
