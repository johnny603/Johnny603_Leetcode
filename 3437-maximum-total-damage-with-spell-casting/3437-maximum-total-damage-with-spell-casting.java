class Solution {
    public long maximumTotalDamage(int[] power) {
        // Count total damage for each unique spell power
        Map<Integer, Long> damageSum = new HashMap<>();
        for (int p : power) {
            damageSum.put(p, damageSum.getOrDefault(p, 0L) + p);
        }

        // Sort unique powers
        List<Integer> unique = new ArrayList<>(damageSum.keySet());
        Collections.sort(unique);

        int n = unique.size();
        long[] dp = new long[n];

        // Initialize first
        dp[0] = damageSum.get(unique.get(0));

        // Fill dp
        for (int i = 1; i < n; i++) {
            int curr = unique.get(i);
            long take = damageSum.get(curr);

            // Find latest j where damage[j] < curr - 2
            int j = i - 1;
            while (j >= 0 && unique.get(j) >= curr - 2) j--;

            if (j >= 0) take += dp[j];

            dp[i] = Math.max(dp[i - 1], take);
        }

        return dp[n - 1];
    }
}
