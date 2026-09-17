/*
7. Reverse Integer   [Medium]
https://leetcode.com/problems/reverse-integer/

Runtime: 1 ms   Memory: 41.2 MB

Given a signed 32-bit integer `x`, return `x` _with its digits reversed_. If reversing `x` causes the value to go outside the signed 32-bit integer range `[-231, 231 - 1]`, then return `0`.

**Assume the environment does not allow you to store 64-bit integers (signed or unsigned).**

**Example 1:**

**Input:** x = 123
**Output:** 321

**Example 2:**

**Input:** x = -123
**Output:** -321

**Example 3:**

**Input:** x = 120
**Output:** 21

**Constraints:**

*   `-231 <= x <= 231 - 1`
*/

class Solution {
    public int reverse(int x) {
        int rev = 0;
        
        while (x != 0) {
            int pop = x % 10;   // extract the last digit
            x /= 10;
            
            // check for overflow before actually updating rev
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0; // overflow
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0; // underflow
            }
            
            rev = rev * 10 + pop;
        }
        
        return rev;
    }
}
