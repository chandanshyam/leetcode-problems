/*
3886. Sum of Sortable Integers   [Hard]
https://leetcode.com/problems/sum-of-sortable-integers/

Runtime: 523 ms   Memory: 88.7 MB

You are given an integer array `nums` of length `n`.

An integer `k` is called **sortable** if `k` **divides** `n` and you can sort `nums` in **non-decreasing** order by sequentially performing the following operations:

*   Partition `nums` into **consecutive subarrays** of length `k`.
*   **Cyclically rotate each subarray independently** any number of times to the left or to the right.

Return an integer denoting the sum of all possible sortable integers `k`.

**Example 1:**

**Input:** nums = \[3,1,2\]

**Output:** 3

**Explanation:**​​​​​​​

*   For `n = 3`, possible divisors are 1 and 3.
*   For `k = 1`: each subarray has one element. No rotation can sort the array.
*   For `k = 3`: the single subarray `[3, 1, 2]` can be rotated once to produce `[1, 2, 3]`, which is sorted.
*   Only `k = 3` is sortable. Hence, the answer is 3.

**Example 2:**

**Input:** nums = \[7,6,5\]

**Output:** 0

**Explanation:**

*   For `n = 3`, possible divisors are 1 and 3.
*   For `k = 1`: each subarray has one element. No rotation can sort the array.
*   For `k = 3`: the single subarray `[7, 6, 5]` cannot be rotated into non-decreasing order.
*   No `k` is sortable. Hence, the answer is 0.

**Example 3:**

**Input:** nums = \[5,8\]

**Output:** 3

**Explanation:**​​​​​​​

*   For `n = 2`, possible divisors are 1 and 2.
*   Since `[5, 8]` is already sorted, every divisor is sortable. Hence, the answer is `1 + 2 = 3`.

**Constraints:**

*   `1 <= n == nums.length <= 105`
*   `1 <= nums[i] <= 105`
*/

/**
 * @param {number[]} nums
 * @return {number}
 */
var sortableIntegers = function (nums) {
    const n = nums.length;
    const fac = [];

    for (let x = 1; x * x <= n; x++) {
        if (n % x === 0) {
            fac.push(x);
            if (n / x !== x) {
                fac.push(n / x);
            }
        }
    }

    const target = [...nums].sort((a, b) => a - b);

    const kmp = (nums1, nums2) => {
        const n = nums1.length,
            m = nums2.length;
        const pi = new Array(m).fill(0);
        let j = 0;

        for (let i = 1; i < m; i++) {
            while (j > 0 && nums2[i] !== nums2[j]) {
                j = pi[j - 1];
            }
            if (nums2[i] === nums2[j]) {
                j++;
            }
            pi[i] = j;
        }

        j = 0;
        for (let i = 0; i < n; i++) {
            while (j > 0 && nums1[i] !== nums2[j]) {
                j = pi[j - 1];
            }
            if (nums1[i] === nums2[j]) {
                j++;
            }
            if (j === m) {
                return true;
            }
        }
        return false;
    };

    const check = (nums1, nums2) => {
        // Check whether cyclic rotations of nums1 can produce nums2
        return kmp([...nums1, ...nums1], nums2);
    };

    let ans = 0;

    for (const d of fac) {
        let allGood = true;
        for (let left = 0; left < n; left += d) {
            const right = left + d - 1;

            if (
                !check(
                    nums.slice(left, right + 1),
                    target.slice(left, right + 1),
                )
            ) {
                allGood = false;
                break;
            }
        }
        if (allGood) {
            ans += d;
        }
    }

    return ans;
};
