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
