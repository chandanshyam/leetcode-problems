/**
 * @param {number[]} nums1
 * @return {boolean}
 */
var uniformArray = function(nums1) {

    let even =0;
    let odd =0;
    let first_odd=-1
    let n=nums1.length;

    for(let i=0;i<n;i++)
    {
        if(nums1[i] %2===0)
        {
            even+=1
        }
        else{
            odd+=1
            if(first_odd===-1) first_odd=i
        }
    }

    let key_val = nums1[first_odd]
    let nums2=[];

     for(let i=0;i<n;i++)
    {
        
        if(nums1[i]%2===1)
        {
            nums2.push(nums1[i])
        }
        else{
            nums2.push(nums1[i]- key_val)
        }
    }
    console.log(nums2);
    return true;
};
