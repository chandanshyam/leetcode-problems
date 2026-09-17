/*
1015. Smallest Integer Divisible by K   [Medium]
https://leetcode.com/problems/smallest-integer-divisible-by-k/

Runtime: 12 ms   Memory: 46.6 MB

Given a positive integer `k`, you need to find the **length** of the **smallest** positive integer `n` such that `n` is divisible by `k`, and `n` only contains the digit `1`.

Return _the **length** of_ `n`. If there is no such `n`, return -1.

**Note:** `n` may not fit in a 64-bit signed integer.

**Example 1:**

**Input:** k = 1
**Output:** 1
**Explanation:** The smallest answer is n = 1, which has length 1.

**Example 2:**

**Input:** k = 2
**Output:** -1
**Explanation:** There is no such positive integer n divisible by 2.

**Example 3:**

**Input:** k = 3
**Output:** 3
**Explanation:** The smallest answer is n = 111, which has length 3.

**Constraints:**

*   `1 <= k <= 105`
*/

class Solution {
    public int smallestRepunitDivByK(int k) {
        if(k%10!=1 && k%10!=3 && k%10!=7 && k%10!=9){
        return -1;}

        Set<Integer> seen = new HashSet<>();
        int n =0;

        for(int l=1;l<=k;++l)
        {
            n= (n * 10 + 1)%k;
            if(n==0)
            {
                return l;
            }
            if(seen.contains(n))
            {
                return -1;
                
            }
            seen.add(n);
        }
        return -1;
    }
}
