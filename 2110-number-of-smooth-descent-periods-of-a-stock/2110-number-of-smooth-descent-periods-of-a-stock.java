class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long[] dp = new long[n];

        // Base case
        dp[0] = 1;

        long total = 1; // dp[0]

        for (int i = 1; i < n; i++) {
            if (prices[i - 1] - prices[i] == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            total += dp[i];
        }

        return total;
    }
}
