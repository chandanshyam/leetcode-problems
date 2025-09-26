class Solution {
    public int evenNumberBitwiseORs(int[] nums) {

        int or =0;

        for(int num : nums)
            {
                if(num%2==0)
                {
                    or |= num;
                }
            }

        return or;
    }
}
