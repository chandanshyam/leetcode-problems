/*
2916. Subarrays Distinct Element Sum of Squares II   [Hard]
https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/

Runtime: 103 ms   Memory: 79.3 MB

You are given a **0-indexed** integer array `nums`.

The **distinct count** of a subarray of `nums` is defined as:

*   Let `nums[i..j]` be a subarray of `nums` consisting of all the indices from `i` to `j` such that `0 <= i <= j < nums.length`. Then the number of distinct values in `nums[i..j]` is called the distinct count of `nums[i..j]`.

Return _the sum of the **squares** of **distinct counts** of all subarrays of_ `nums`.

Since the answer may be very large, return it **modulo** `109 + 7`.

A subarray is a contiguous **non-empty** sequence of elements within an array.

**Example 1:**

**Input:** nums = \[1,2,1\]
**Output:** 15
**Explanation:** Six possible subarrays are:
\[1\]: 1 distinct value
\[2\]: 1 distinct value
\[1\]: 1 distinct value
\[1,2\]: 2 distinct values
\[2,1\]: 2 distinct values
\[1,2,1\]: 2 distinct values
The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 + 22 + 22 + 22 = 15.

**Example 2:**

**Input:** nums = \[2,2\]
**Output:** 3
**Explanation:** Three possible subarrays are:
\[2\]: 1 distinct value
\[2\]: 1 distinct value
\[2,2\]: 1 distinct value
The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 = 3.

**Constraints:**

*   `1 <= nums.length <= 105`
*   `1 <= nums[i] <= 105`
*/

/**
 * @param {number[]} nums
 * @return {number}
 */
class BIT {
    constructor(n) {
        this.size = n;
        this._tree1 = new Float64Array(n + 2);
        this._tree2 = new Float64Array(n + 2);
    }

    _lowbit(index) {
        return index & -index;
    }

    add(left, right, delta) {
        this._add(left, delta);
        this._add(right + 1, -delta);
    }

    query(left, right) {
        return this._query(right) - this._query(left - 1);
    }

    _add(index, delta) {
        index += 1;
        let rawIndex = index;
        while (index <= this.size) {
            this._tree1[index] += delta;
            this._tree2[index] += rawIndex * delta;
            index += this._lowbit(index);
        }
    }

    _query(index) {
        index += 1;
        let rawIndex = index + 1;
        if (index > this.size) {
            index = this.size;
        }
        let res = 0;
        while (index > 0) {
            res += rawIndex * this._tree1[index] - this._tree2[index];
            index -= this._lowbit(index);
        }
        return res;
    }
}

var sumCounts = function (A) {
    let n = A.length;
    let res = 0n,
        cur = 0n;
    let bit = new BIT(n + 10);
    let seen = new Map();
    let mod = 1000000007n;
    for (let i = 0; i < n; i++) {
        let a = A[i];
        let j = (seen.has(a) ? seen.get(a) : -1) + 1;
        let q = BigInt(bit.query(j, i));
        cur = (cur + q * 2n + BigInt(i - j + 1)) % mod;
        res = (res + cur) % mod;
        bit.add(j, i, 1);
        seen.set(a, i);
    }
    return Number(res);
};
