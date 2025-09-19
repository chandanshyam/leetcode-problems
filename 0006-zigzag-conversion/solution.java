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

