class Solution {
    public boolean containsDuplicate(int[] nums) {
    
     HashSet<Integer> s = new HashSet<Integer>();
         int c =0;

        for(int num:  nums){
        s.add(num);
        }
        return !(s.size() == nums.length);
   

    }
}

