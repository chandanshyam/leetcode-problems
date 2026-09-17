/*
2975. Maximum Square Area by Removing Fences From a Field   [Medium]
https://leetcode.com/problems/maximum-square-area-by-removing-fences-from-a-field/

Runtime: 671 ms   Memory: 274.8 MB

There is a large `(m - 1) x (n - 1)` rectangular field with corners at `(1, 1)` and `(m, n)` containing some horizontal and vertical fences given in arrays `hFences` and `vFences` respectively.

Horizontal fences are from the coordinates `(hFences[i], 1)` to `(hFences[i], n)` and vertical fences are from the coordinates `(1, vFences[i])` to `(m, vFences[i])`.

Return _the **maximum** area of a **square** field that can be formed by **removing** some fences (**possibly none**) or_ `-1` _if it is impossible to make a square field_.

Since the answer may be large, return it **modulo** `109 + 7`.

**Note:** The field is surrounded by two horizontal fences from the coordinates `(1, 1)` to `(1, n)` and `(m, 1)` to `(m, n)` and two vertical fences from the coordinates `(1, 1)` to `(m, 1)` and `(1, n)` to `(m, n)`. These fences **cannot** be removed.

**Example 1:**

![](https://assets.leetcode.com/uploads/2023/11/05/screenshot-from-2023-11-05-22-40-25.png)

**Input:** m = 4, n = 3, hFences = \[2,3\], vFences = \[2\]
**Output:** 4
**Explanation:** Removing the horizontal fence at 2 and the vertical fence at 2 will give a square field of area 4.

**Example 2:**

![](https://assets.leetcode.com/uploads/2023/11/22/maxsquareareaexample1.png)

**Input:** m = 6, n = 7, hFences = \[2\], vFences = \[4\]
**Output:** -1
**Explanation:** It can be proved that there is no way to create a square field by removing fences.

**Constraints:**

*   `3 <= m, n <= 109`
*   `1 <= hFences.length, vFences.length <= 600`
*   `1 < hFences[i] < m`
*   `1 < vFences[i] < n`
*   `hFences` and `vFences` are unique.
*/

class Solution {
    static final int MOD = 1000000007;

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> hGaps = collectGaps(hFences, m);
        Set<Integer> vGaps = collectGaps(vFences, n);

        long best = 0;
        for (int g : hGaps) {
            if (vGaps.contains(g)) {
                best = Math.max(best, g);
            }
        }

        if (best == 0) return -1;
        return (int)((best * best) % MOD);
    }

    private Set<Integer> collectGaps(int[] fences, int limit) {
        List<Integer> points = new ArrayList<>();
        points.add(1);
        points.add(limit);

        for (int f : fences) points.add(f);
        Collections.sort(points);

        Set<Integer> gaps = new HashSet<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                gaps.add(points.get(j) - points.get(i));
            }
        }
        return gaps;
    }
}
