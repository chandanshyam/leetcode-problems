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
