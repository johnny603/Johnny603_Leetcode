class Solution {

    // Modulo required by the problem
    private static final int MOD = 1_000_000_007;

    /*
        memo[z][o][last]

        z = zeros left to place
        o = ones left to place
        last = last digit placed in the array

        last = 0 → array currently ends with 0
        last = 1 → array currently ends with 1
    */
    private Long[][][] memo;

    // Maximum allowed consecutive identical digits
    private int limit;

    public int numberOfStableArrays(int zero, int one, int limit) {

        // Initialize memo table
        memo = new Long[zero + 1][one + 1][2];

        this.limit = limit;

        /*
           Count arrays ending in 0 and arrays ending in 1
        */
        long answer =
                (countArrays(zero, one, 0)
               + countArrays(zero, one, 1)) % MOD;

        return (int) answer;
    }

    /**
     * DFS + Memoization
     *
     * zerosLeft = number of 0s still available
     * onesLeft  = number of 1s still available
     * lastDigit = last digit placed in the array
     */
    private long countArrays(int zerosLeft, int onesLeft, int lastDigit) {

        // Invalid state used for prefix subtraction
        if (zerosLeft < 0 || onesLeft < 0)
            return 0;

        /*
        Base cases
        */

        // Only ones remain
        if (zerosLeft == 0) {

            /*
             Valid only if:
             - we are continuing a run of 1s
             - remaining ones do not exceed the limit
            */
            return (lastDigit == 1 && onesLeft <= limit) ? 1 : 0;
        }

        // Only zeros remain
        if (onesLeft == 0) {

            /*
             Valid only if:
             - we are continuing a run of 0s
             - remaining zeros do not exceed the limit
            */
            return (lastDigit == 0 && zerosLeft <= limit) ? 1 : 0;
        }

        // Return memoized result if already computed
        if (memo[zerosLeft][onesLeft][lastDigit] != null)
            return memo[zerosLeft][onesLeft][lastDigit];

        long result;

        /*
        Transition
        */

        if (lastDigit == 0) {

            /*
             We place a 0 at this position.

             Total possibilities:
             arrays that previously ended with
             either 0 or 1 after consuming one zero
            */
            long allWays =
                    countArrays(zerosLeft - 1, onesLeft, 0)
                  + countArrays(zerosLeft - 1, onesLeft, 1);

            /*
             Remove invalid sequences where we would
             exceed the allowed run of zeros.

             This subtracts arrays where we already
             had 'limit' zeros before adding this one.
            */
            long overLimitWays =
                    countArrays(
                        zerosLeft - limit - 1,
                        onesLeft,
                        1
                    );

            result = (allWays - overLimitWays + MOD) % MOD;

        } else {

            /*
             We place a 1 at this position.
            */
            long allWays =
                    countArrays(zerosLeft, onesLeft - 1, 0)
                  + countArrays(zerosLeft, onesLeft - 1, 1);

            /*
             Remove invalid sequences where we would
             exceed the allowed run of ones.
            */
            long overLimitWays =
                    countArrays(
                        zerosLeft,
                        onesLeft - limit - 1,
                        0
                    );

            result = (allWays - overLimitWays + MOD) % MOD;
        }

        // Store result for reuse
        memo[zerosLeft][onesLeft][lastDigit] = result;

        return result;
    }
}