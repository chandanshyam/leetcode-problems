class Solution {

    static class FenwickMax {
        int n;
        int[] bit;
        FenwickMax(int n)
        {
            this.n = n;
            this.bit = new int[n + 2];
        }

        void update(int idx, int val)
        {
            for(int i= idx; i<=n;i+= i & -i)
                {
                    bit[i] = Math.max( bit[i], val);
                    
                }
        }

        int query(int idx)
        {
            int res = 0;
            for(int i= idx; i>0 ; i-= i & -i)
                {
                    res = Math.max(res, bit[i]);
                }
            return res;
        }
        
        
    }

    
    public int maxCapacity(int[] costs, int[] capacity, int budget) {

        int n = costs.length;
        int[][] a = new int[n][2];
        int maxCost =0;

        for(int i=0;i<n;i++)
            {
                a[i][0] = costs[i];
                a[i][1] = capacity[i];
                maxCost = Math.max(maxCost, costs[i]);
                
            }

        Arrays.sort(a, Comparator.comparingInt(x -> x[0]));
        FenwickMax bit = new FenwickMax(maxCost);

        int ans =0;

        for(int i=0;i<n;i++)
            {
                 int cost = a[i][0];
        int cap = a[i][1];

        if(cost < budget) ans = Math.max(ans, cap);

        int rem = (budget - 1) - cost;
        if(rem >= 1)
        {
            int bestPrev = bit.query(Math.min(rem, maxCost));
            if(bestPrev > 0) ans = Math.max(ans, cap + bestPrev);
            
        }

        bit.update(cost, cap);
    }
       
    return ans;
    }
}
