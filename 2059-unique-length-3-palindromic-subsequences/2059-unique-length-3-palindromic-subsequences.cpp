class Solution {
public:
    int countPalindromicSubsequence(string s) {
        int n = s.length();
        int result = 0;

        // Loop through all 26 possible outer characters
        for (char c = 'a'; c <= 'z'; c++) {
            int left = -1;
            int right = -1;

            // Find first and last occurrence
            for (int i = 0; i < n; i++) {
                if (s[i] == c) {
                    if (left == -1) left = i;
                    right = i;
                }
            }

            // Need at least two occurrences
            if (left != -1 && right != -1 && left < right) {

                // Track distinct characters between left and right
                bool seen[26] = {false};

                for (int i = left + 1; i < right; i++) {
                    seen[s[i] - 'a'] = true;
                }

                // Count how many unique mid characters exist
                for (int k = 0; k < 26; k++) {
                    if (seen[k]) result++;
                }
            }
        }

        return result;
    }
};
