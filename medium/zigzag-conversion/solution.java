/*
6. Zigzag Conversion   [Medium]
https://leetcode.com/problems/zigzag-conversion/

Runtime: 4 ms   Memory: 44.7 MB

The string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: `"PAHNAPLSIIGYIR"`

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

**Example 1:**

**Input:** s = "PAYPALISHIRING", numRows = 3
**Output:** "PAHNAPLSIIGYIR"

**Example 2:**

**Input:** s = "PAYPALISHIRING", numRows = 4
**Output:** "PINALSIGYAHRPI"
**Explanation:**
P     I    N
A   L S  I G
Y A   H R
P     I

**Example 3:**

**Input:** s = "A", numRows = 1
**Output:** "A"

**Constraints:**

*   `1 <= s.length <= 1000`
*   `s` consists of English letters (lower-case and upper-case), `','` and `'.'`.
*   `1 <= numRows <= 1000`
*/

class Solution {
    public String convert(String s, int numRows) {
        int n = s.length();
        if (numRows == 1 || numRows >= n) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int r = 0, dir = 1; // 1 = down, -1 = up
        for (int i = 0; i < n; i++) {
            rows[r].append(s.charAt(i));
            if (r == 0) dir = 1;
            else if (r == numRows - 1) dir = -1;
            r += dir;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) ans.append(row);
        return ans.toString();
    }
}
