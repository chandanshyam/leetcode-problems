/*
3726. Remove Zeros in Decimal Representation   [Easy]
https://leetcode.com/problems/remove-zeros-in-decimal-representation/

Runtime: 9 ms   Memory: 42 MB

You are given a **positive** integer `n`.

Return the integer obtained by removing all zeros from the decimal representation of `n`.

**Example 1:**

**Input:** n = 1020030

**Output:** 123

**Explanation:**

After removing all zeros from 1**0**2**00**3**0**, we get 123.

**Example 2:**

**Input:** n = 1

**Output:** 1

**Explanation:**

1 has no zero in its decimal representation. Therefore, the answer is 1.

**Constraints:**

*   `1 <= n <= 1015`
*/

class Solution {
    public long removeZeros(long n) {
        String s=Long.toString(n);
        String s1="";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='0'){
                s1+=s.charAt(i);
            }
        }
        return Long.parseLong(s1);
    }
}
