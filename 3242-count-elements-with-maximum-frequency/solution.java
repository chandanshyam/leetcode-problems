class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int c=0;

        for(int num: nums)
        {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        } 


        int maxValue = Collections.max(freq.values());

        for(int value : freq.values())
        {
            if(value == maxValue)
            {
                c+=maxValue;
            }
        }
        
        return c;
    }
}
