/*
347. Top K Frequent Elements   [Medium]
https://leetcode.com/problems/top-k-frequent-elements/

Runtime: 11 ms   Memory: 49.8 MB

Given an integer array `nums` and an integer `k`, return _the_ `k` _most frequent elements_. You may return the answer in **any order**.

**Example 1:**

**Input:** nums = \[1,1,1,2,2,3\], k = 2

**Output:** \[1,2\]

**Example 2:**

**Input:** nums = \[1\], k = 1

**Output:** \[1\]

**Example 3:**

**Input:** nums = \[1,2,1,2,1,2,3,1,3,2\], k = 2

**Output:** \[1,2\]

**Constraints:**

*   `1 <= nums.length <= 105`
*   `-104 <= nums[i] <= 104`
*   `k` is in the range `[1, the number of unique elements in the array]`.
*   It is **guaranteed** that the answer is **unique**.

**Follow up:** Your algorithm's time complexity must be better than `O(n log n)`, where n is the array's size.
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
//create a tracking Hashmap
        HashMap<Integer,Integer> ans = new HashMap<>();
        for(int n : nums){
            ans.put(n, ans.getOrDefault(n,0)+1);
        }
//create the bucket List
        List<Integer>[] freq = new List[nums.length+1];
 //Initialize  empty arrays as buckets to each list
       for(int i=0; i< freq.length; i++)
    {
        freq[i] = new ArrayList<>();
    } 
// add each frequency value to a bucket 
    for(Map.Entry<Integer, Integer> entry: ans.entrySet())
    {
        freq[entry.getValue()].add(entry.getKey());
    }
   int[] res = new int[k];
    int index =0;
    for(int i=freq.length-1;i > 0 && index < k;i--)
    {
        for(int n  :freq[i])
        {
            res[index++]=n;
           if(index==k){   return res; }  }
    }
return res;     
    }
}
