class Solution {
    public long removeZeros(long n) {
        String s=Long.toString(n);
        String s1="";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='0'){
                s1+=s.charAt(i);
            }
        }
        return Long.parseLong(s1);
    }
}
