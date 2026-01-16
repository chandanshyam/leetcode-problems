class Solution {
    public int maxSubArray(int[] nums) {
        int n=nums.length;
      int maxi = Integer.MIN_VALUE; 
        int sum = 0;

        for (int i = 0; i < n; i++) 
        {

            sum +=nums[i];

            if (sum < nums[i]) {
                sum = nums[i];
            }
            
            if(sum > maxi){
                maxi = sum;
            }
        }
        return maxi;  
    }
}
