class Solution {
    public int residuePrefixes(String s) {
        int count =0;
        Set<Character> seen = new HashSet<>();

        for(int i=0;i<s.length();i++)
            {
                seen.add(s.charAt(i));
                int distinctC = seen.size();
                int lengthMod3 = (i + 1)%3;

                if(distinctC==lengthMod3)
                {
                    count++;
                }
            }
        return count;
    }
}
