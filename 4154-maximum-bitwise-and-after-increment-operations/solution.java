class Solution {
    public int maximumAND(int[] nums, int k, int m) {
        int ans =0;
        for(int bit =30; bit>=0; bit--)
            {
                int candidate = ans | (1<<bit);
                if(canAchieve(nums, k, m, candidate))
                {
                    ans = candidate;
                }
            }
        return ans;
    }

    private boolean canAchieve(int[]nums, int k, int m, int target)
    {
        long[] costs = new long[nums.length];
        for(int i =0; i < nums.length; i++)
            {
                costs[i] = costToSetBits(nums[i], target);
            }
            Arrays.sort(costs);
        long total =0;
        for(int i=0;i<m; i++)
            {
                total+=costs[i];
            }
            return total<=k;
    }

    private long costToSetBits(int val, int target){
        if(target ==0)
        {
            return 0;
        }

        if((val &target) == target)
        {
            return 0;
            
        }


        if(val <= target)
        {
            return target - val;
        }

       long x = val;
        
       while((x & target) != target)
           {
               long missing = target & ~x;
               int shift = Long.numberOfTrailingZeros(missing);
               x = ((x >> shift) + 1) << shift;
               
           }
        return x - val;
    }
}
