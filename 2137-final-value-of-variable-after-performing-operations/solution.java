class Solution {
    public int finalValueAfterOperations(String[] operations) {

          int res =0;

        for(String operation : operations)
         {
            if(operation.equals("--X") || operation.equals("X--"))
        {
            res-=1;
        }
        else{
            res+=1;
        }
    }

     return res;
        
    }
}
