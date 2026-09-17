/*
3804. Number of Centered Subarrays   [Medium]
https://leetcode.com/problems/number-of-centered-subarrays/

Runtime: 106 ms   Memory: 47 MB

You are given an integer array `nums`.

A **subarray** of `nums` is called **centered** if the sum of its elements is **equal to at least one** element within that **same subarray**.

Return the number of **centered subarrays** of `nums`.

**Example 1:**

**Input:** nums = \[-1,1,0\]

**Output:** 5

**Explanation:**

*   All single-element subarrays (`[-1]`, `[1]`, `[0]`) are centered.
*   The subarray `[1, 0]` has a sum of 1, which is present in the subarray.
*   The subarray `[-1, 1, 0]` has a sum of 0, which is present in the subarray.
*   Thus, the answer is 5.

**Example 2:**

**Input:** nums = \[2,-3\]

**Output:** 2

**Explanation:**

Only single-element subarrays (`[2]`, `[-3]`) are centered.

**Constraints:**

*   `1 <= nums.length <= 500`
*   `-105 <= nums[i] <= 105`
*/

class Solution {
    public int centeredSubarrays(int[] nums) {

        int n = nums.length;
        int count = 0;

        for(int i=0;i<n;i++)
            {
                long sum =0;
                Set<Long> elements = new HashSet<>();

                for(int j=i;j<n; j++)
                    {
                        sum+=nums[j];
                        elements.add((long) nums[j]);

                        if(elements.contains(sum))
                        {
                            count++;
                        }
                    }
            }
        return count;
    }
}
