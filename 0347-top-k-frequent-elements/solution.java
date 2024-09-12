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
