class Solution {
    public int vowelConsonantScore(String s) {

        int v =0, c=0;

        for(int i=0;i<s.length();i++)
            {
                char ch = s.charAt(i);
                if(ch >= 'a' && ch <= 'z')
                {
                    if(isVowel(ch)) v++;
                    else c++;
                }
            }
        return (c==0) ? 0 : ( v / c);
    }

    private boolean isVowel(char ch)
    {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch ==  'u';
    }
}
