class Solution {
    public boolean isAnagram(String s, String t) {

   int count[]=new int[26];

       for(char i:s.toCharArray()){
           count[i-'a']++;
       }
       for(char i:t.toCharArray()){
           count[i-'a']--;
       }
       for(int i=0;i<26;i++){
           if(count[i]!=0){
               return false;
           }
       }

return true;
    }

}
