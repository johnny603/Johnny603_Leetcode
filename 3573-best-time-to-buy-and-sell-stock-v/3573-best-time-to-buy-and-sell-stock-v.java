class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long NEG = Long.MIN_VALUE / 4;

        long[][] dp = new long[k + 1][3];

        // initialize
        for (int t = 0; t <= k; t++) {
            dp[t][0] = NEG;
            dp[t][1] = NEG;
            dp[t][2] = NEG;
        }

        dp[0][0] = 0;
        dp[0][1] = -prices[0]; // buy
        dp[0][2] = prices[0];  // short sell

        for (int i = 1; i < n; i++) {
            long price = prices[i];
            long[][] next = new long[k + 1][3];

            for (int t = 0; t <= k; t++) {
                for (int s = 0; s < 3; s++) {
                    next[t][s] = dp[t][s];
                }
            }

            for (int t = 0; t <= k; t++) {
                // stay / open positions
                next[t][1] = Math.max(next[t][1], dp[t][0] - price); // buy
                next[t][2] = Math.max(next[t][2], dp[t][0] + price); // short sell

                if (t + 1 <= k) {
                    // close long
                    next[t + 1][0] = Math.max(
                        next[t + 1][0],
                        dp[t][1] + price
                    );

                    // close short
                    next[t + 1][0] = Math.max(
                        next[t + 1][0],
                        dp[t][2] - price
                    );
                }
            }

            dp = next;
        }

        long ans = 0;
        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, dp[t][0]);
        }
        return ans;
    }
}
