import java.util.HashMap;
import java.util.ArrayList; 

class Solution {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> m = new HashMap<>();

        int[] res = new int[2];

        for(int i =0;i< nums.length;i++)
        {
            
            // add the index as key and the difference as the value in the map 
            // put the difference as the key and index as the value in the map
            //if the key already exists return i and value[key]

            if(m.containsKey(nums[i])){
                res[0] = m.get(nums[i]);
                res[1] = i;
            }
            else{
                m.put(target-nums[i], i);
            }
        
        }

        return res;
        
    }
}
