/*
22. Generate Parentheses   [Medium]
https://leetcode.com/problems/generate-parentheses/

Runtime: 0 ms   Memory: 44.4 MB

Given `n` pairs of parentheses, write a function to _generate all combinations of well-formed parentheses_.

**Example 1:**

**Input:** n = 3
**Output:** \["((()))","(()())","(())()","()(())","()()()"\]

**Example 2:**

**Input:** n = 1
**Output:** \["()"\]

**Constraints:**

*   `1 <= n <= 8`
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        recurse(res, 0, 0, sb, n);
        return res;

        
    }


    public void recurse(List<String> res, int left, int right, StringBuilder sb,int  n)
    {
        if(sb.length()==2*n)
        {
            res.add(sb.toString());
            return ;
        }

        if(left < n) {
            sb.append('(');
            recurse(res, left + 1, right, sb, n);
            sb.deleteCharAt(sb.length()-1);
            }

        if(right < left) {
             sb.append(')');
            recurse(res, left, right+1, sb, n);
            sb.deleteCharAt(sb.length()-1);
            }


    }
}
