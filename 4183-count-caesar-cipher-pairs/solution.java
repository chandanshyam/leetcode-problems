class Solution {
    public long countPairs(String[] words) {
        Map<String, Integer> signC = new HashMap<>();


        for(String word: words)
            {
               int base = word.charAt(0) - 'a';
                StringBuilder key = new StringBuilder();

                for(char c: word.toCharArray())
                    {
                        int diff = (c-'a'-base + 26) % 26;
                        key.append(diff).append(',');
                    }
                    signC.put(key.toString(), signC.getOrDefault(key.toString(), 0) + 1);
            }

        long result =0;

        for(int count: signC.values())
            {
                result+= (long) count * (count - 1) /2;
            }

        return result;
    }
}
