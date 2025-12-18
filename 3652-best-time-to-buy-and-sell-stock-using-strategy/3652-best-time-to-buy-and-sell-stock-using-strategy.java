class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        int half = k / 2;

        long baseProfit = 0;
        for (int i = 0; i < n; i++) {
            baseProfit += (long) strategy[i] * prices[i];
        }

        long[] prefA = new long[n + 1];
        long[] prefB = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefA[i + 1] = prefA[i] + (long) strategy[i] * prices[i];
            prefB[i + 1] = prefB[i] + (long) (1 - strategy[i]) * prices[i];
        }

        long maxDelta = 0;

        for (int l = 0; l + k <= n; l++) {
            long firstHalfLoss =
                prefA[l + half] - prefA[l];

            long secondHalfGain =
                prefB[l + k] - prefB[l + half];

            long delta = -firstHalfLoss + secondHalfGain;
            maxDelta = Math.max(maxDelta, delta);
        }

        return baseProfit + maxDelta;
    }
}
