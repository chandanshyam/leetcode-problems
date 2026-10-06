/*
47. Permutations II   [Medium]
https://leetcode.com/problems/permutations-ii/

Runtime: 3 ms   Memory: 59.6 MB

Given a collection of numbers, `nums`, that might contain duplicates, return _all possible unique permutations **in any order**._

**Example 1:**

**Input:** nums = \[1,1,2\]
**Output:**
\[\[1,1,2\],
 \[1,2,1\],
 \[2,1,1\]\]

**Example 2:**

**Input:** nums = \[1,2,3\]
**Output:** \[\[1,2,3\],\[1,3,2\],\[2,1,3\],\[2,3,1\],\[3,1,2\],\[3,2,1\]\]

**Constraints:**

*   `1 <= nums.length <= 8`
*   `-10 <= nums[i] <= 10`
*/

function permuteUnique(nums) {
  const res = [];

  function backtrack(start) {
    if (start === nums.length) {
      res.push([...nums]);
      return;
    }

    const seen = new Set();
    for (let i = start; i < nums.length; i++) {
      if (seen.has(nums[i])) continue;
      seen.add(nums[i]);

      [nums[start], nums[i]] = [nums[i], nums[start]];
      backtrack(start + 1);
      [nums[start], nums[i]] = [nums[i], nums[start]];
    }
  }

  backtrack(0);
  return res;
}
