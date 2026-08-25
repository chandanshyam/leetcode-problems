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
