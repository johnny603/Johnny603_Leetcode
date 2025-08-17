/*
Initialize dp[0] = 1.0.
Maintain a sliding window windowSum = sum of probabilities of the last maxPts reachable states.
For 1 <= i <= n:
dp[i] = windowSum / maxPts.
If i < k, add dp[i] to windowSum (Alice can continue).
If i - maxPts >= 0, subtract dp[i - maxPts] from windowSum.
Answer = sum(dp[k] ... dp[n]) (since those are valid stopping scores).
*/


class Solution {
    public double new21Game(int n, int k, int maxPts) {
        // Edge case: If Alice never needs to draw (k == 0) 
        // OR Alice can always stop before exceeding n (k + maxPts > n)
        if (k == 0 || n >= k - 1 + maxPts) return 1.0;

        double[] dp = new double[n + 1];
        dp[0] = 1.0;
        double windowSum = 1.0;  // running sum of the last maxPts probabilities
        double result = 0.0;

        for (int i = 1; i <= n; i++) {
            dp[i] = windowSum / maxPts;

            if (i < k) {
                // Still in play, Alice can draw further → add dp[i] to window
                windowSum += dp[i];
            } else {
                // Alice stops here → add dp[i] to result
                result += dp[i];
            }

            // Slide the window
            if (i - maxPts >= 0) {
                windowSum -= dp[i - maxPts];
            }
        }

        return result;
    }
}
