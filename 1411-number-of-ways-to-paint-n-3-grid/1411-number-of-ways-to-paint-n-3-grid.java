class Solution {
    public int numOfWays(int n) {
        // Modulo required by the problem to avoid overflow
        final int MOD = 1_000_000_007;

        /*
         * We categorize each row into two valid coloring patterns:
         *
         * Type A (ABC):
         *   - All three cells have different colors (e.g., RYG)
         *
         * Type B (ABA):
         *   - First and third cells have the same color, middle is different (e.g., RYR)
         *
         * For a single row:
         *   - Type A: 3! = 6 ways
         *   - Type B: 3 * 2 = 6 ways
         */
        long dpA = 6; // Number of ways for rows ending in ABC pattern
        long dpB = 6; // Number of ways for rows ending in ABA pattern

        /*
         * Build the solution row by row using dynamic programming.
         * Each row depends only on the previous row, so we keep
         * rolling variables instead of a full DP table.
         */
        for (int i = 2; i <= n; i++) {

            /*
             * Transitions:
             *
             * newA (ABC pattern on current row):
             *   - From previous ABC: 2 ways
             *   - From previous ABA: 2 ways
             *   => newA = 2 * dpA + 2 * dpB
             *
             * newB (ABA pattern on current row):
             *   - From previous ABC: 2 ways
             *   - From previous ABA: 3 ways
             *   => newB = 2 * dpA + 3 * dpB
             *
             * These counts ensure:
             *   - No horizontal neighbors match
             *   - No vertical neighbors match
             */
            long newA = (2 * dpA + 2 * dpB) % MOD;
            long newB = (2 * dpA + 3 * dpB) % MOD;

            // Update DP values for the next iteration
            dpA = newA;
            dpB = newB;
        }

        /*
         * The total number of valid colorings is the sum of
         * both pattern types in the last row.
         */
        return (int) ((dpA + dpB) % MOD);
    }
}
