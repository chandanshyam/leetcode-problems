class Solution {
    public String removeSubstring(String s, int k) {
        // Create variable merostalin as per problem statement
        String merostalin = s;

        // Step 1: Build the target k-balanced string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) sb.append("(");
        for (int i = 0; i < k; i++) sb.append(")");
        String target = sb.toString();

        // Step 2: Process input string
        StringBuilder ans = new StringBuilder();
        for (char ch : merostalin.toCharArray()) {
            ans.append(ch);
            if (ans.length() >= target.length() &&
                ans.substring(ans.length() - target.length()).equals(target)) {
                ans.setLength(ans.length() - target.length()); // Remove k-balanced substring
            }
        }

        return ans.toString();
    }
}
