/*
1401. Circle and Rectangle Overlapping   [Medium]
https://leetcode.com/problems/circle-and-rectangle-overlapping/

Runtime: 0 ms   Memory: 54 MB

You are given a circle represented as `(radius, xCenter, yCenter)` and an axis-aligned rectangle represented as `(x1, y1, x2, y2)`, where `(x1, y1)` are the coordinates of the bottom-left corner, and `(x2, y2)` are the coordinates of the top-right corner of the rectangle.

Return `true` _if the circle and rectangle are overlapped otherwise return_ `false`. In other words, check if there is **any** point `(xi, yi)` that belongs to the circle and the rectangle at the same time.

**Example 1:**

![](https://assets.leetcode.com/uploads/2020/02/20/sample_4_1728.png)

**Input:** radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
**Output:** true
**Explanation:** Circle and rectangle share the point (1,0).

**Example 2:**

**Input:** radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
**Output:** false

**Example 3:**

![](https://assets.leetcode.com/uploads/2020/02/20/sample_2_1728.png)

**Input:** radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
**Output:** true

**Constraints:**

*   `1 <= radius <= 2000`
*   `-104 <= xCenter, yCenter <= 104`
*   `-104 <= x1 < x2 <= 104`
*   `-104 <= y1 < y2 <= 104`
*/

/**
 * @param {number} radius
 * @param {number} xCenter
 * @param {number} yCenter
 * @param {number} x1
 * @param {number} y1
 * @param {number} x2
 * @param {number} y2
 * @return {boolean}
 */
function distance(ux, uy, vx, vy) {
    return (ux - vx) ** 2 + (uy - vy) ** 2;
}

var checkOverlap = function (radius, xCenter, yCenter, x1, y1, x2, y2) {
    /* The center of the circle is inside the rectangle */
    if (x1 <= xCenter && xCenter <= x2 && y1 <= yCenter && yCenter <= y2) {
        return true;
    }
    /* The center of the circle is above the rectangle */
    if (
        x1 <= xCenter &&
        xCenter <= x2 &&
        y2 <= yCenter &&
        yCenter <= y2 + radius
    ) {
        return true;
    }
    /* The center of the circle is below the rectangle */
    if (
        x1 <= xCenter &&
        xCenter <= x2 &&
        y1 - radius <= yCenter &&
        yCenter <= y1
    ) {
        return true;
    }
    /* The center of the circle is to the left of the rectangle */
    if (
        x1 - radius <= xCenter &&
        xCenter <= x1 &&
        y1 <= yCenter &&
        yCenter <= y2
    ) {
        return true;
    }
    /* The center of the circle is to the right of the rectangle */
    if (
        x2 <= xCenter &&
        xCenter <= x2 + radius &&
        y1 <= yCenter &&
        yCenter <= y2
    ) {
        return true;
    }
    /* The upper-left corner of the rectangle */
    if (distance(xCenter, yCenter, x1, y2) <= radius * radius) {
        return true;
    }
    /* The lower-left corner of the rectangle */
    if (distance(xCenter, yCenter, x1, y1) <= radius * radius) {
        return true;
    }
    /* The upper-right corner of the rectangle */
    if (distance(xCenter, yCenter, x2, y2) <= radius * radius) {
        return true;
    }
    /* The lower-right corner of the rectangle */
    if (distance(xCenter, yCenter, x2, y1) <= radius * radius) {
        return true;
    }
    /* No intersection */
    return false;
};
