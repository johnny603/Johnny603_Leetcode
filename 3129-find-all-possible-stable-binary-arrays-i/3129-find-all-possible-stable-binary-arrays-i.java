class Solution {

    // Constant for modulo operations (problem requirement)
    private static final int MOD = 1_000_000_007;

    // Memoization table
    // memo[z][o][last]
    // z = zeros remaining
    // o = ones remaining
    // last = last digit placed (0 or 1)
    private Long[][][] memo;

    // Maximum number of identical digits allowed consecutively
    private int limit;

    public int numberOfStableArrays(int zero, int one, int limit) {

        // Initialize memo table
        memo = new Long[zero + 1][one + 1][2];

        this.limit = limit;

        // Count arrays ending with 0 and arrays ending with 1
        long result =
                (countStableArrays(zero, one, 0) +
                 countStableArrays(zero, one, 1)) % MOD;

        return (int) result;
    }

    /**
     * Recursive DP with memoization.
     *
     * @param zerosRemaining number of zeros still available to place
     * @param onesRemaining  number of ones still available to place
     * @param lastDigit      last digit placed in the array (0 or 1)
     *
     * @return number of valid stable arrays satisfying constraints
     */
    private long countStableArrays(int zerosRemaining,
                                   int onesRemaining,
                                   int lastDigit) {

        // Invalid state (used when subtracting prefix ranges)
        if (zerosRemaining < 0 || onesRemaining < 0) {
            return 0;
        }

        /*
        Base cases
        */

        // Only ones remain
        if (zerosRemaining == 0) {

            // Valid only if we are continuing a run of 1s
            // and the run length does not exceed limit
            return (lastDigit == 1 && onesRemaining <= limit) ? 1 : 0;
        }

        // Only zeros remain
        if (onesRemaining == 0) {

            // Valid only if we are continuing a run of 0s
            // and the run length does not exceed limit
            return (lastDigit == 0 && zerosRemaining <= limit) ? 1 : 0;
        }

        // Return memoized value if already computed
        if (memo[zerosRemaining][onesRemaining][lastDigit] != null) {
            return memo[zerosRemaining][onesRemaining][lastDigit];
        }

        long result;

        /*
        Transition logic
        */

        if (lastDigit == 0) {

            /*
            We place a 0 at this position.

            Total ways:
            combine arrays that previously ended with 0 or 1
            after using one zero.
            */

            long totalWays =
                    countStableArrays(zerosRemaining - 1, onesRemaining, 0)
                  + countStableArrays(zerosRemaining - 1, onesRemaining, 1);

            /*
            Remove invalid cases where we would exceed
            the allowed number of consecutive zeros.

            This subtracts arrays where we already had
            'limit' zeros before adding this one.
            */

            long invalidWays =
                    countStableArrays(
                            zerosRemaining - limit - 1,
                            onesRemaining,
                            1);

            result = (totalWays - invalidWays + MOD) % MOD;

        } else {

            /*
            We place a 1 at this position.
            */

            long totalWays =
                    countStableArrays(zerosRemaining, onesRemaining - 1, 0)
                  + countStableArrays(zerosRemaining, onesRemaining - 1, 1);

            /*
            Remove cases exceeding the limit
            of consecutive ones.
            */

            long invalidWays =
                    countStableArrays(
                            zerosRemaining,
                            onesRemaining - limit - 1,
                            0);

            result = (totalWays - invalidWays + MOD) % MOD;
        }

        // Store result in memo table
        memo[zerosRemaining][onesRemaining][lastDigit] = result;

        return result;
    }
}