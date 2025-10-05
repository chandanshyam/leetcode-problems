class Solution {
public:
    int longestSubsequence(vector<int>& nums) {
        int total=0;
        int n=nums.size();
        for(auto it:nums){
            total^=it;
        }
        if(total!=0){
            return n;
        }
        bool ans=true;
        for(auto it:nums){
            if(it!=0){
                ans=false;
                break;
            }
        }
        if(ans==true){
            return 0;
        }
        return n-1;
    }
};
