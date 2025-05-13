class Solution {
    public int lengthAfterTransformations(String s, int t) {
        final int MOD = 1_000_000_007;
        long[] freq = new long[26];

        // Initialize frequency of each character in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int time = 0; time < t; time++) {
            long[] nextFreq = new long[26];
            for (int i = 0; i < 26; i++) {
                if (i == 25) { // 'z'
                    nextFreq[0] = (nextFreq[0] + freq[i]) % MOD; // 'a'
                    nextFreq[1] = (nextFreq[1] + freq[i]) % MOD; // 'b'
                } else {
                    nextFreq[i + 1] = (nextFreq[i + 1] + freq[i]) % MOD;
                }
            }
            freq = nextFreq;
        }

        // Sum final frequencies
        long totalLength = 0;
        for (long count : freq) {
            totalLength = (totalLength + count) % MOD;
        }

        return (int) totalLength;
    }
}
