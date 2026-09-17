/*
78. Subsets   [Medium]
https://leetcode.com/problems/subsets/

Runtime: 0 ms   Memory: 56.6 MB

Given an integer array `nums` of **unique** elements, return _all possible_ _subsets_ _(the power set)_.

The solution set **must not** contain duplicate subsets. Return the solution in **any order**.

**Example 1:**

**Input:** nums = \[1,2,3\]
**Output:** \[\[\],\[1\],\[2\],\[1,2\],\[3\],\[1,3\],\[2,3\],\[1,2,3\]\]

**Example 2:**

**Input:** nums = \[0\]
**Output:** \[\[\],\[0\]\]

**Constraints:**

*   `1 <= nums.length <= 10`
*   `-10 <= nums[i] <= 10`
*   All the numbers of `nums` are **unique**.
*/

/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsets = function(nums) {
    
    let res = [];

    function backtrack(start, currPath)
    {
        res.push([...currPath]);

        for(let i=start;i<nums.length;i++)
        {
            currPath.push(nums[i]);

            backtrack(i+1, currPath);


            currPath.pop();
        }
    }
    
    backtrack(0, []);

    return res;
};
