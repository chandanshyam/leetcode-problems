class Solution {
    public int maxArea(int[] height) {

        int l =0;
        int r = height.length -1;
        int maxC = 0;

        while(l < r)
        {

            int a = (r-l) * Math.min(height[r], height[l]);
            maxC = Math.max(maxC, a);


            if(height[l] > height[r])
            {
                r--;
            }
            else{
                l++;
            }


        }
        
        return maxC;
    }
}
