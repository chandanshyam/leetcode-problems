/*
3718. Smallest Missing Multiple of K   [Easy]
https://leetcode.com/problems/smallest-missing-multiple-of-k/

Runtime: 1 ms   Memory: 56 MB

Given an integer array `nums` and an integer `k`, return the **smallest positive multiple** of `k` that is **missing** from `nums`.

A **multiple** of `k` is any positive integer divisible by `k`.

**Example 1:**

**Input:** nums = \[8,2,3,4,6\], k = 2

**Output:** 10

**Explanation:**

The multiples of `k = 2` are 2, 4, 6, 8, 10, 12... and the smallest multiple missing from `nums` is 10.

**Example 2:**

**Input:** nums = \[1,4,7,10,15\], k = 5

**Output:** 5

**Explanation:**

The multiples of `k = 5` are 5, 10, 15, 20... and the smallest multiple missing from `nums` is 5.

**Constraints:**

*   `1 <= nums.length <= 100`
*   `1 <= nums[i] <= 100`
*   `1 <= k <= 100`
*/

/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */

var missingMultiple = function(nums, k) {
    const s = new Set();

    for (let num of nums)
    {
        s.add(num);
    }

    for(let i=1; i<= nums.length; i++)
    {
        if(!s.has(k*i))
        {
            return k * i;
        }
    }

    return k * (nums.length+1);
};
