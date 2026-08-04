class Solution {
    public List<Integer> findMissingElements(int[] nums) {

        int s=0;
        int l=0;


        Arrays.sort(nums);


        s= nums[0];
        l=nums[nums.length-1];
        List<Integer> res = new ArrayList<>();

        //ADD ALL data into a set 
        Set<Integer> se = new HashSet<>();

        for(int num : nums)
        {
            se.add(num);
        }
        while( s!= l)
        {
            if(se.contains(s)) {s+=1;}
            else {res.add(s);
            s+=1;}
           
        }

    return res;


        
    }
}
