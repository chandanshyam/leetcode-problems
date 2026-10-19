/*
1569. Number of Ways to Reorder Array to Get Same BST   [Hard]
https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/

Runtime: 1264 ms   Memory: 120 MB

Given an array `nums` that represents a permutation of integers from `1` to `n`. We are going to construct a binary search tree (BST) by inserting the elements of `nums` in order into an initially empty BST. Find the number of different ways to reorder `nums` so that the constructed BST is identical to that formed from the original array `nums`.

*   For example, given `nums = [2,1,3]`, we will have 2 as the root, 1 as a left child, and 3 as a right child. The array `[2,3,1]` also yields the same BST but `[3,2,1]` yields a different BST.

Return _the number of ways to reorder_ `nums` _such that the BST formed is identical to the original BST formed from_ `nums`.

Since the answer may be very large, **return it modulo** `109 + 7`.

**Example 1:**

![](https://assets.leetcode.com/uploads/2026/02/23/1569_ex0.png)

**Input:** nums = \[2,1,3\]
**Output:** 1
**Explanation:** We can reorder nums to be \[2,3,1\] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.

**Example 2:**

![](https://assets.leetcode.com/uploads/2026/02/23/1569_ex.png)

**Input:** nums = \[3,4,5,1,2\]
**Output:** 5
**Explanation:** The following 5 arrays will yield the same BST: 
\[3,1,2,4,5\]
\[3,1,4,2,5\]
\[3,1,4,5,2\]
\[3,4,1,2,5\]
\[3,4,1,5,2\]

**Example 3:**

![](https://assets.leetcode.com/uploads/2026/02/23/1569_ex2.png)

**Input:** nums = \[1,2,3\]
**Output:** 0
**Explanation:** There are no other orderings of nums that will yield the same BST.

**Constraints:**

*   `1 <= nums.length <= 1000`
*   `1 <= nums[i] <= nums.length`
*   All integers in `nums` are **distinct**.
*/

/**
 * @param {number[]} nums
 * @return {number}
 */
var numOfWays = function (nums) {
    const MOD = 1000000007n;
    const n = nums.length;

    const C = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(0n));
    for (let i = 0; i <= n; i++) {
        C[i][0] = 1n;
        for (let j = 1; j <= i; j++)
            C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
    }

    const ways = (a) => {
        const m = a.length;
        if (m <= 2) return 1n;
        const left = [], right = [];
        for (let i = 1; i < m; i++) {
            if (a[i] < a[0]) left.push(a[i]);
            else right.push(a[i]);
        }
        return C[m - 1][left.length] * ways(left) % MOD * ways(right) % MOD;
    };

    return Number((ways(nums) - 1n + MOD) % MOD);
};
