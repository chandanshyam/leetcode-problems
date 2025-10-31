class Solution {
    public int[] getSneakyNumbers(int[] nums) {

        Set<Integer> s = new HashSet<>();

        List<Integer> res = new ArrayList<>();

        for(int num : nums)
        {
            if(s.contains(num))
            {
                res.add(num);
            }
            s.add(num);
        }
        
        return new int[]{res.get(0), res.get(1)};
    }
}
