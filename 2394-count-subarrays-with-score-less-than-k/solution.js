/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var countSubarrays = function(nums, k) {

    let count=0;
    let left=0;
    let len= nums.length;
    let currSum =0;

    for(let right=0;right<len;right++)
    {
        currSum+=nums[right];

       let  window = right - left + 1;

        while(left<=right && currSum * window >= k)
        {
            currSum-=nums[left];
            left++;
            window = right - left + 1;
        }
        count+= right - left + 1;
    }

    return count;

}
