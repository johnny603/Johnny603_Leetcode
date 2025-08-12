class Solution {
    public int numberOfWays(int n, int x) {
        // PLAN:
        // 1. Precompute all powers of integers^x that are <= n
        //    - Since the integers must be unique and positive, we only consider base values starting from 1.
        //    - Stop when base^x > n.
        //
        // 2. Use a DP approach similar to the "subset sum" problem:
        //    - dp[i][sum] represents the number of ways to form 'sum' using the first i numbers (powers).
        //    - We iterate over each available power and update ways to form sums.
        //
        // 3. Base case:
        //    - dp[0][0] = 1 (1 way to make sum = 0 with no numbers).
        //
        // 4. Transition:
        //    - For each power p:
        //        dp[i][sum] = dp[i-1][sum]  // exclude p
        //        if sum >= p:
        //            dp[i][sum] += dp[i-1][sum - p]  // include p
        //
        // 5. The answer will be dp[m][n] where m is the number of available powers.
        //
        // 6. Since result can be large, take modulo 1_000_000_007 in each addition.
        //
        // Time complexity:
        //    O(m * n) where m is number of powers (<= n^(1/x))
        // Space complexity:
        //    Can use 2D dp or 1D dp (space optimized).
        
        int MOD = 1_000_000_007;
        
        // Step 1: Generate all powers <= n
        List<Integer> powers = new ArrayList<>();
        for (int base = 1; ; base++) {
            long power = (long) Math.pow(base, x);
            if (power > n) break;
            powers.add((int) power);
        }
        
        // Step 2: 1D DP array for space optimization
        int[] dp = new int[n + 1];
        dp[0] = 1; // 1 way to make sum = 0
        
        // Step 3: Iterate over each power and update dp
        for (int p : powers) {
            for (int sum = n; sum >= p; sum--) {
                dp[sum] = (dp[sum] + dp[sum - p]) % MOD;
            }
        }
        
        return dp[n];
    }
}
