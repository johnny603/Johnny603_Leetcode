class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int total = 0;

        // We iterate over all possible zero-counts (k) such that k^2 <= n.
        // This is the critical optimization.
        for (int k = 0; k + k * k <= n; k++) {

            int lastInvalidLeft = -1;      // Last left index where window became invalid
            int[] freq = new int[2];       // freq[0] = zeros, freq[1] = ones
            int left = 0;

            // Expand right pointer
            for (int right = 0; right < n; right++) {

                // Add current character
                freq[s.charAt(right) - '0']++;

                // Shrink window from the left to maintain minimal valid structure
                while (left < right) {
                    char c = s.charAt(left);

                    // Case 1: Remove an extra zero if zero-count exceeded k
                    if (c == '0' && freq[0] > k) {
                        freq[0]--;
                        lastInvalidLeft = left;
                        left++;
                    }
                    // Case 2: Remove a one if doing so keeps ones >= k*k
                    else if (c == '1' && freq[1] - 1 >= k * k) {
                        freq[1]--;
                        left++;
                    } 
                    // Cannot shrink further
                    else {
                        break;
                    }
                }

                // If window has exactly k zeros and >= k^2 ones, it's valid
                if (freq[0] == k && freq[1] >= k * k) {
                    // Count all valid substrings ending at `right`
                    // They start from: (lastInvalidLeft+1) ... left
                    total += left - lastInvalidLeft;
                }
            }
        }

        return total;
    }
}
