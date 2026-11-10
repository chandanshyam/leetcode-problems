/*
462. Minimum Moves to Equal Array Elements II   [Medium]
https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/

Runtime: 7 ms   Memory: 54.8 MB

Given an integer array `nums` of size `n`, return _the minimum number of moves required to make all array elements equal_.

In one move, you can increment or decrement an element of the array by `1`.

Test cases are designed so that the answer will fit in a **32-bit** integer.

**Example 1:**

**Input:** nums = \[1,2,3\]
**Output:** 2
**Explanation:**
Only two moves are needed (remember each move increments or decrements one element):
\[1,2,3\]  =>  \[2,2,3\]  =>  \[2,2,2\]

**Example 2:**

**Input:** nums = \[1,10,2,9\]
**Output:** 16

**Constraints:**

*   `n == nums.length`
*   `1 <= nums.length <= 105`
*   `-109 <= nums[i] <= 109`
*/

/**
 * @param {number[]} nums
 * @return {number}
 */
var minMoves2 = function(nums) {
    const med = quickselect(nums, nums.length >> 1);
    return nums.reduce((moves, x) => moves + Math.abs(x - med), 0);
};

function quickselect(a, k) {
    let lo = 0, hi = a.length - 1;
    while (true) {
        const pivot = a[lo + Math.floor(Math.random() * (hi - lo + 1))];
        let lt = lo, i = lo, gt = hi;  // a[lo..lt) < pivot, a[lt..i) == pivot, a(gt..hi] > pivot
        while (i <= gt) {
            if (a[i] < pivot) swap(a, lt++, i++);
            else if (a[i] > pivot) swap(a, i, gt--);
            else i++;
        }
        if (k < lt) hi = lt - 1;
        else if (k > gt) lo = gt + 1;
        else return pivot;
    }
}

function swap(a, i, j) {
    const t = a[i]; a[i] = a[j]; a[j] = t;
}
