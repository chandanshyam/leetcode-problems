/**
 * @param {number[]} nums
 * @return {number}
 */
var smallestIndex = function(nums) {


    for(let i=0;i<nums.length;i++)
    {

        let num = nums[i];
        let digit =0;
        let currSum =0;
       while(num > 0)
       {
        digit = num % 10;
        currSum += digit;

        num = Math.floor(num /10);

       }

       if(currSum === i)
       {
        return i;
       }
    }

    return -1;
    
};
