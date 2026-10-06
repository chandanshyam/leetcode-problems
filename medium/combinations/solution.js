/*
77. Combinations   [Medium]
https://leetcode.com/problems/combinations/

Runtime: 63 ms   Memory: 127.4 MB

Given two integers `n` and `k`, return _all possible combinations of_ `k` _numbers chosen from the range_ `[1, n]`.

You may return the answer in **any order**.

**Example 1:**

**Input:** n = 4, k = 2
**Output:** \[\[1,2\],\[1,3\],\[1,4\],\[2,3\],\[2,4\],\[3,4\]\]
**Explanation:** There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., \[1,2\] and \[2,1\] are considered to be the same combination.

**Example 2:**

**Input:** n = 1, k = 1
**Output:** \[\[1\]\]
**Explanation:** There is 1 choose 1 = 1 total combination.

**Constraints:**

*   `1 <= n <= 20`
*   `1 <= k <= n`
*/

/**
 * @param {number} n
 * @param {number} k
 * @return {number[][]}
 */
var combine = function(n, k) {

    let res = [];
    let path = [];



    function backtrack(start)
    {
        if(path.length=== k)
        {
            res.push([...path]);
            return;
        }

        for(let i=start;i<=n;i++)
        {
            path.push(i);
            backtrack(i+1)  ;
            path.pop();
           
        }

        }

        backtrack(1);

        return res;
    
};
