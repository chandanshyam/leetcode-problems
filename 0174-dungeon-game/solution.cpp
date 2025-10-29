class Solution {
    int n, m;
    int dfs(int i, int j, vector<vector<int>>& nums, vector<vector<int>> &dp){
        if(i >= n || j >= m) return INT_MIN;
        if(i==n-1 && j==m-1) return nums[i][j];
        if(dp[i][j] != -1) return  dp[i][j];
        int ans=nums[i][j];
        int res=nums[i][j]+max(dfs(i+1, j, nums, dp), dfs(i, j+1, nums, dp));
        return dp[i][j]=min(ans, res);
    }
public:
    int calculateMinimumHP(vector<vector<int>>& dungeon) {
        n=dungeon.size();
        m=dungeon[0].size();
        vector<vector<int>> dp(n+1, vector<int> (m+1, -1));
        int res=dfs(0, 0, dungeon, dp);
        if(res>=0) return 1;
        res=-1*res;
        return res+1;
    }
};
