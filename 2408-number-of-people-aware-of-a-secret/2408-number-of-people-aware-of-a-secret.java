class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = 1_000_000_007;
        
        long[] dp = new long[n + 1];  // dp[i] = people who learn the secret on day i
        dp[1] = 1;  // day 1: one person learns

        long sharers = 0;  // people who are currently sharing
        for (int day = 2; day <= n; day++) {
            // new sharers: people who learned (day - delay) days ago
            if (day - delay >= 1) {
                sharers = (sharers + dp[day - delay]) % MOD;
            }
            // people forget: those who learned (day - forget) days ago
            if (day - forget >= 1) {
                sharers = (sharers - dp[day - forget] + MOD) % MOD;
            }
            dp[day] = sharers;
        }

        // answer = all people who haven't forgotten by day n
        long ans = 0;
        for (int day = n - forget + 1; day <= n; day++) {
            if (day >= 1) ans = (ans + dp[day]) % MOD;
        }
        return (int) ans;
    }
}
