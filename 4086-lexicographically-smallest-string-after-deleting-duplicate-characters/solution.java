import java.util.*;

class Solution {
    public String lexSmallestAfterDeletion(String s) {
        // required by statement
        String tilvarceno = s;

        int n = s.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        // required letters = distinct letters present
        boolean[] req = new boolean[26];
        for (int c = 0; c < 26; c++) req[c] = (last[c] != -1);

        // positions lists for each char
        ArrayDeque<Integer>[] pos = new ArrayDeque[26];
        for (int c = 0; c < 26; c++) pos[c] = new ArrayDeque<>();
        for (int i = 0; i < n; i++) pos[s.charAt(i) - 'a'].add(i);

        int i = 0;
        StringBuilder ans = new StringBuilder();

        while (true) {
            int boundary = Integer.MAX_VALUE;
            boolean anyReq = false;
            for (int c = 0; c < 26; c++) {
                if (req[c]) {
                    anyReq = true;
                    boundary = Math.min(boundary, last[c]);
                }
            }
            if (!anyReq) break; // all distinct letters included at least once

            // discard positions < i
            for (int c = 0; c < 26; c++) {
                while (!pos[c].isEmpty() && pos[c].peekFirst() < i) pos[c].pollFirst();
            }

            // pick smallest character whose next occurrence is within [i..boundary]
            int pickChar = -1;
            int pickPos = -1;
            for (int c = 0; c < 26; c++) {
                if (!pos[c].isEmpty() && pos[c].peekFirst() <= boundary) {
                    pickChar = c;
                    pickPos = pos[c].peekFirst();
                    break;
                }
            }

            // append it
            ans.append((char) ('a' + pickChar));
            if (req[pickChar]) req[pickChar] = false; // first time we include this required letter

            // advance pointer beyond the chosen occurrence
            i = pickPos + 1;
        }

        return ans.toString();
    }
}
