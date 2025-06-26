public class Solution {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int count = 0;
        long value = 0;
        int power = 0;

        // Traverse from right to left (least significant to most significant)
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '0') {
                count++; // Always include 0
            } else {
                if (power < 32 && value + (1L << power) <= k) {
                    value += (1L << power);
                    count++;
                }
                // Otherwise, skip the '1' to avoid exceeding k
            }
            power++;
        }

        return count;
    }
}
