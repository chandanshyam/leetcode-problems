/*
40. Combination Sum II   [Medium]
https://leetcode.com/problems/combination-sum-ii/

Runtime: 1 ms   Memory: 56 MB

Given a collection of candidate numbers (`candidates`) and a target number (`target`), find all unique combinations in `candidates` where the candidate numbers sum to `target`.

Each number in `candidates` may only be used **once** in the combination.

**Note:** The solution set must not contain duplicate combinations.

**Example 1:**

**Input:** candidates = \[10,1,2,7,6,1,5\], target = 8
**Output:** 
\[
\[1,1,6\],
\[1,2,5\],
\[1,7\],
\[2,6\]
\]

**Example 2:**

**Input:** candidates = \[2,5,2,1,2\], target = 5
**Output:** 
\[
\[1,2,2\],
\[5\]
\]

**Constraints:**

*   `1 <= candidates.length <= 100`
*   `1 <= candidates[i] <= 50`
*   `1 <= target <= 30`
*/

/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function(candidates, target) {

    const res = [];


    let sum =0;
    candidates.sort((a,b) => a - b);
    function backtrack(path, index)
    {
        if(sum > target)
        {
            return;
        }

        if(sum===target)
        {
                res.push([...path]);
                return;
        }

        for(let i=index;i<candidates.length;i++)
        {

            if(i > index && candidates[i] === candidates[i-1]) continue;
            sum+=candidates[i];
            path.push(candidates[i]);
            backtrack(path, i + 1);
            sum-=candidates[i];
            path.pop();

        }
    }

    backtrack([], 0);

    
    return res;
};
