/*
3875. Construct Uniform Parity Array I   [Easy]
https://leetcode.com/problems/construct-uniform-parity-array-i/

Runtime: 33 ms   Memory: 61.5 MB

You are given an array `nums1` of `n` **distinct** integers.

You want to construct another array `nums2` of length `n` such that the elements in `nums2` are either **all odd or all even**.

For each index `i`, you must choose **exactly one** of the following (in any order):

*   `nums2[i] = nums1[i]`
*   `nums2[i] = nums1[i] - nums1[j]`, for an index `j != i`

Return `true` if it is possible to construct such an array, otherwise, return `false`.

**Example 1:**

**Input:** nums1 = \[2,3\]

**Output:** true

**Explanation:**

*   Choose `nums2[0] = nums1[0] - nums1[1] = 2 - 3 = -1`.
*   Choose `nums2[1] = nums1[1] = 3`.
*   `nums2 = [-1, 3]`, and both elements are odd. Thus, the answer is `true`​​​​​​​.

**Example 2:**

**Input:** nums1 = \[4,6\]

**Output:** true

**Explanation:**​​​​​​​

*   Choose `nums2[0] = nums1[0] = 4`.
*   Choose `nums2[1] = nums1[1] = 6`.
*   `nums2 = [4, 6]`, and all elements are even. Thus, the answer is `true`.

**Constraints:**

*   `1 <= n == nums1.length <= 100`
*   `1 <= nums1[i] <= 100`
*   `nums1` consists of distinct integers.
*/

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
