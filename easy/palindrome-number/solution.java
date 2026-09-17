/*
9. Palindrome Number   [Easy]
https://leetcode.com/problems/palindrome-number/

Runtime: 4 ms   Memory: 44.4 MB

Given an integer `x`, return `true` if `x` is a **palindrome**, and `false` otherwise.

**Example 1:**

**Input:** x = 121
**Output:** true
**Explanation:** 121 reads as 121 from left to right and from right to left.

**Example 2:**

**Input:** x = -121
**Output:** false
**Explanation:** From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

**Example 3:**

**Input:** x = 10
**Output:** false
**Explanation:** Reads 01 from right to left. Therefore it is not a palindrome.

**Constraints:**

*   `-231 <= x <= 231 - 1`

**Follow up:** Could you solve it without converting the integer to a string?
*/

class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers and multiples of 10 (except 0) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        while (x > reversedHalf) {
            int digit = x % 10;
            reversedHalf = reversedHalf * 10 + digit;
            x /= 10;
        }

        // For even length: x == reversedHalf
        // For odd length: x == reversedHalf / 10 (ignore middle digit)
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
