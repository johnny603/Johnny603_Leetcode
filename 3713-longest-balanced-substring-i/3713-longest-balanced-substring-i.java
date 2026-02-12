class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int ans = 1; // At minimum, any single character substring is balanced

        // Try every possible starting index of a substring
        for (int i = 0; i < n; i++) {

            // freq keeps track of how many times each letter appears
            // We use an array because there are only 26 letters
            int[] freq = new int[26];

            int unique = 0;  // number of characters that appear at least once
            int maxFreq = 0;   // highest frequency of any character in this substring

            // Extend the substring one character at a time
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';

                // If this character appears for the first time,
                // we increase the number of distinct characters
                if (freq[idx] == 0) {
                    unique++;
                }

                freq[idx]++;

                // Track the largest frequency seen so far
                // This helps us test if all characters are equal
                maxFreq = Math.max(maxFreq, freq[idx]);

                int len = j - i + 1;

                /*
                 WHY this condition works:

                 A substring is balanced if all distinct characters
                 appear the same number of times.

                 Suppose:
                 - there are D distinct characters
                 - each appears exactly K times

                 Then total length = D * K

                 maxFreq represents the largest count of any character.
                 If len == unique * maxFreq, the only way this can
                 be true is if every character appears maxFreq times.

                 If even one character had fewer counts,
                 the equality would fail.
                */
                if (len == unique * maxFreq) {
                    ans = Math.max(ans, len);
                }
            }
        }

        return ans;
    }
}
