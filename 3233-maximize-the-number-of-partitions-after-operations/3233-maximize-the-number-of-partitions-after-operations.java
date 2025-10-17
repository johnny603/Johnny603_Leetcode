class Solution {
    // Memoization map: [position, currentDistinctMask, canChange] -> max partitions possible
    private Map<List<Integer>, Integer> memo = new HashMap<>();
    private String s;     // Input string
    private int k;        // Maximum allowed distinct characters per partition

    public int maxPartitionsAfterOperations(String s, int k) {
        this.s = s;
        this.k = k;
        // Start DFS from position 0, empty partition (mask=0), and one change available
        return dfs(0, 0, 1);
    }

    /**
     * DFS with memoization.
     * @param pos       Current index in the string.
     * @param mask      Bitmask representing which characters are in the current partition.
     * @param canChange 1 if we can still change one character, 0 if we already used that change.
     * @return Maximum number of partitions possible from this point onward.
     */
    private int dfs(int pos, int mask, int canChange) {
        // Base case: reached end of string — count the current partition
        if (pos == s.length()) {
            return 1;
        }

        // Check memo table
        var key = List.of(pos, mask, canChange);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Bit representation of the current character
        int currentCharBit = 1 << (s.charAt(pos) - 'a');

        // Option 1: Do NOT change the current character
        int newMask = mask | currentCharBit;
        int best = 0;

        if (Integer.bitCount(newMask) > k) {
            // Too many distinct chars → start a new partition
            best = dfs(pos + 1, currentCharBit, canChange) + 1;
        } else {
            // Still valid → continue current partition
            best = dfs(pos + 1, newMask, canChange);
        }

        // Option 2: Try CHANGING the current character (if we still can)
        if (canChange > 0) {
            // Try replacing current char with all 26 possible characters
            for (int newChar = 0; newChar < 26; ++newChar) {
                int changedMask = mask | (1 << newChar);

                if (Integer.bitCount(changedMask) > k) {
                    // Exceeds k → start a new partition with the changed char
                    best = Math.max(best, dfs(pos + 1, 1 << newChar, 0) + 1);
                } else {
                    // Still valid → continue in current partition
                    best = Math.max(best, dfs(pos + 1, changedMask, 0));
                }
            }
        }

        // Store and return the computed result
        memo.put(key, best);
        return best;
    }
}
