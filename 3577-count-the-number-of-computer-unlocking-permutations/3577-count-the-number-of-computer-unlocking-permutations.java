class Solution {
    public int countPermutations(int[] complexity) {
        // Use modulo to avoid integer overflow for large counts
        final int MOD = 1_000_000_007;

        // totalWays starts at 1 because the first computer is fixed as the root
        long totalWays = 1;

        // Iterate over all other computers starting from index 1
        for (int pos = 1; pos < complexity.length; pos++) {

            // If any computer has complexity <= the root's complexity,
            // then it cannot appear *after* the root in a valid unlocking order.
            // Therefore, no valid permutation exists.
            if (complexity[pos] <= complexity[0]) {
                return 0;
            }

            // Multiply by 'pos' because at each step the number of valid ways
            // grows like a factorial (1 * 1 * 2 * 3 * ...).
            totalWays = (totalWays * pos) % MOD;
        }

        // Return the result modulo 1e9+7
        return (int) totalWays;
    }
}
