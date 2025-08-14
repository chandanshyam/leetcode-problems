class Solution {
    public int lengthOfLongestSubstring(String s) {

        int maxL = 0;
        Set<Character> set = new HashSet<>();

        int l=0;

        for(int r=0;r<s.length();r++)
        {
            while(set.contains(s.charAt(r)))
            {
                set.remove(s.charAt(l));
                l++;
            }

            set.add(s.charAt(r));

            maxL = Math.max(maxL, r-l+1);
        }

        return maxL;

}
}

//add p  r=1
//add w   r=2
//reach w again l=1
//set(has only p now)
// l=1


