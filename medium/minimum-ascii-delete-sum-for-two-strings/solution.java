/*
712. Minimum ASCII Delete Sum for Two Strings   [Medium]
https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/

Runtime: 18 ms   Memory: 43.2 MB

Given two strings `s1` and `s2`, return _the lowest **ASCII** sum of deleted characters to make two strings equal_.

**Example 1:**

**Input:** s1 = "sea", s2 = "eat"
**Output:** 231
**Explanation:** Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.

**Example 2:**

**Input:** s1 = "delete", s2 = "leet"
**Output:** 403
**Explanation:** Deleting "dee" from "delete" to turn the string into "let",
adds 100\[d\] + 101\[e\] + 101\[e\] to the sum.
Deleting "e" from "leet" adds 101\[e\] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.

**Constraints:**

*   `1 <= s1.length, s2.length <= 1000`
*   `s1` and `s2` consist of lowercase English letters.
*/

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[] dp = new int[m + 1];

        for (int j = m - 1; j >= 0; j--) {
            dp[j] = dp[j + 1] + s2.charAt(j);
        }

        for (int i = n - 1; i >= 0; i--) {
            int prev = dp[m];
            dp[m] += s1.charAt(i);

            for (int j = m - 1; j >= 0; j--) {
                int temp = dp[j];
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[j] = prev;
                } else {
                    dp[j] = Math.min(
                        s1.charAt(i) + dp[j],
                        s2.charAt(j) + dp[j + 1]
                    );
                }
                prev = temp;
            }
        }
        return dp[0];
    }
}
