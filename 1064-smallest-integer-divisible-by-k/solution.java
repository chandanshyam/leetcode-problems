class Solution {
    public int smallestRepunitDivByK(int k) {
        if(k%10!=1 && k%10!=3 && k%10!=7 && k%10!=9){
        return -1;}

        Set<Integer> seen = new HashSet<>();
        int n =0;

        for(int l=1;l<=k;++l)
        {
            n= (n * 10 + 1)%k;
            if(n==0)
            {
                return l;
            }
            if(seen.contains(n))
            {
                return -1;
                
            }
            seen.add(n);
        }
        return -1;
    }
}
