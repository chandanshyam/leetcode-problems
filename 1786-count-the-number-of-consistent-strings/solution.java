class Solution {
    public int countConsistentStrings(String allowed, String[] words) {


Set<Character> s = new HashSet<>();

for(char c :allowed.toCharArray())
{
    s.add(c);
}

int count=0;

for(String k: words)
{
    int flag=1;

    for(int i=0;i<k.length();i++)
    {
        if(!s.contains(k.charAt(i)))
        {
            flag=0;
            break;
        }
    }
      count+=flag;
}
return count;
}
}

