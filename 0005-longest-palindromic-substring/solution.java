class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) return s;

        int bestL = 0, bestR = 0; // inclusive ends

        for (int i = 0; i < n; i++) {
            // odd length
            int[] p1 = expand(s, i, i);
            if (p1[1] - p1[0] > bestR - bestL) { bestL = p1[0]; bestR = p1[1]; }

            // even length
            int[] p2 = expand(s, i, i + 1);
            if (p2[1] - p2[0] > bestR - bestL) { bestL = p2[0]; bestR = p2[1]; }
        }

        return s.substring(bestL, bestR + 1);
    }

    private int[] expand(String s, int l, int r) {
        int n = s.length();
        while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        // palindrome is [l+1, r-1]
        return new int[]{l + 1, r - 1};
    }
}

