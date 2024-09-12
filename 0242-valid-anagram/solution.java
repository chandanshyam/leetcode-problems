class Solution {
    public boolean isAnagram(String s, String t) {

        if(s.length()!=t.length())
        {
            return false;
        }
        int[] ta=new int[26];

        for(int i=0;i<s.length();i++)
        {
             ta[s.charAt(i)-'a']+=1;
            ta[t.charAt(i)-'a']-=1;
        }

        for(int n: ta)
        {
            if(n>0)
            {
                return false;
            }
            
        }
        return true;

    }
}
