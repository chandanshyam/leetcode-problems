/*
3505. Minimum Operations to Make Elements Within K Subarrays Equal   [Hard]
https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-subarrays-equal/

Runtime: 1167 ms   Memory: 124.2 MB

You are given an integer array `nums` and two integers, `x` and `k`. You can perform the following operation any number of times (**including zero**):

*   Increase or decrease any element of `nums` by 1.

Return the **minimum** number of operations needed to have **at least** `k` _non-overlapping subarrays_ of size **exactly** `x` in `nums`, where all elements within each subarray are equal.

**Example 1:**

**Input:** nums = \[5,-2,1,3,7,3,6,4,-1\], x = 3, k = 2

**Output:** 8

**Explanation:**

*   Use 3 operations to add 3 to `nums[1]` and use 2 operations to subtract 2 from `nums[3]`. The resulting array is `[5, 1, 1, 1, 7, 3, 6, 4, -1]`.
*   Use 1 operation to add 1 to `nums[5]` and use 2 operations to subtract 2 from `nums[6]`. The resulting array is `[5, 1, 1, 1, 7, 4, 4, 4, -1]`.
*   Now, all elements within each subarray `[1, 1, 1]` (from indices 1 to 3) and `[4, 4, 4]` (from indices 5 to 7) are equal. Since 8 total operations were used, 8 is the output.

**Example 2:**

**Input:** nums = \[9,-2,-2,-2,1,5\], x = 2, k = 2

**Output:** 3

**Explanation:**

*   Use 3 operations to subtract 3 from `nums[4]`. The resulting array is `[9, -2, -2, -2, -2, 5]`.
*   Now, all elements within each subarray `[-2, -2]` (from indices 1 to 2) and `[-2, -2]` (from indices 3 to 4) are equal. Since 3 operations were used, 3 is the output.

**Constraints:**

*   `2 <= nums.length <= 105`
*   `-106 <= nums[i] <= 106`
*   `2 <= x <= nums.length`
*   `1 <= k <= 15`
*   `2 <= k * x <= nums.length`
*/

/**
 * @param {number[]} nums
 * @param {number} x
 * @param {number} k
 * @return {number}
 */
var minOperations = function (nums, x, k) {
    const n = nums.length;
    const left = new MaxPriorityQueue();
    const right = new MinPriorityQueue();
    const delayed = new Map();
    let leftSize = 0,
        rightSize = 0;
    let sumL = 0,
        sumR = 0;
    const ops = Array(n).fill(0);

    const prune = (heap) => {
        while (!heap.isEmpty() && (delayed.get(heap.front()) || 0) > 0) {
            const num = heap.dequeue();
            delayed.set(num, delayed.get(num) - 1);
        }
    };

    const balance = () => {
        if (leftSize > rightSize + 1) {
            const num = left.dequeue();
            right.enqueue(num);
            sumR += num;
            sumL -= num;
            ++rightSize;
            --leftSize;
            prune(left);
        } else if (rightSize > leftSize) {
            const num = right.dequeue();
            left.enqueue(num);
            sumL += num;
            sumR -= num;
            ++leftSize;
            --rightSize;
            prune(right);
        }
    };

    for (let i = 0; i < n; ++i) {
        const num = nums[i];
        if (left.isEmpty() || num <= left.front()) {
            left.enqueue(num);
            ++leftSize;
            sumL += num;
        } else {
            right.enqueue(num);
            ++rightSize;
            sumR += num;
        }
        balance();
        if (i >= x) {
            const num = nums[i - x];
            delayed.set(num, (delayed.get(num) || 0) + 1);
            if (num <= left.front()) {
                --leftSize;
                sumL -= num;
                if (num === left.front()) {
                    prune(left);
                }
            } else {
                --rightSize;
                sumR -= num;
                if (num === right.front()) {
                    prune(right);
                }
            }
            balance();
        }
        if (i >= x - 1) {
            const mid = left.front();
            ops[i] = sumR - sumL + mid * (leftSize - rightSize);
        }
    }

    const dp = Array.from({ length: n }, () => Array(k + 1).fill(Infinity));
    for (let i = 0; i < n; ++i) {
        dp[i][0] = 0;
    }
    for (let j = 1; j <= k; ++j) {
        if (j === 1) {
            dp[x - 1][j] = ops[x - 1];
        } else {
            dp[j * x - 1][j] = dp[(j - 1) * x - 1][j - 1] + ops[j * x - 1];
        }
    }
    for (let j = 1; j <= k; ++j) {
        for (let i = j * x; i < n; ++i) {
            dp[i][j] = Math.min(dp[i - x][j - 1] + ops[i], dp[i - 1][j]);
        }
    }
    return dp[n - 1][k];
};
