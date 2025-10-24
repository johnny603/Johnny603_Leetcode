class Solution {
    public int nextBeautifulNumber(int n) {
        // Start checking numbers greater than n
        for (int x = n + 1; ; x++) {
            // Convert current number x to string
            String s = String.valueOf(x);
            
            // Initialize an array to count digit frequencies (0–9)
            int[] freq = new int[10];
            
            // Count frequency of each digit in x
            for (char c : s.toCharArray()) {
                freq[c - '0']++;
            }
            
            // Check if x is numerically balanced
            boolean balanced = true;
            for (char c : s.toCharArray()) {
                int d = c - '0';
                if (freq[d] != d) {
                    balanced = false;
                    break;
                }
            }
            
            // If balanced, we found the smallest number > n
            if (balanced) {
                return x;
            }
        }
    }
}
