class Solution {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        long MOD = 1_000_000_007;

        long[] dp = new long[n + 1];  // dp[i] = ways for prefix length i
        long[] pref = new long[n + 1]; // prefix sum of dp

        dp[0] = 1;
        pref[0] = 1;

        Deque<Integer> maxD = new ArrayDeque<>();
        Deque<Integer> minD = new ArrayDeque<>();

        int L = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            // push to max deque
            while (!maxD.isEmpty() && maxD.peekLast() < x)
                maxD.pollLast();
            maxD.addLast(x);

            // push to min deque
            while (!minD.isEmpty() && minD.peekLast() > x)
                minD.pollLast();
            minD.addLast(x);

            // shrink left boundary while window invalid
            while (maxD.peekFirst() - minD.peekFirst() > k) {
                if (nums[L] == maxD.peekFirst()) maxD.pollFirst();
                if (nums[L] == minD.peekFirst()) minD.pollFirst();
                L++;
            }

            // now all j ∈ [L..i] form valid segment ending at i
            // dp[i+1] = sum(dp[L..i])
            long sum = (pref[i] - (L > 0 ? pref[L - 1] : 0) + MOD) % MOD;
            dp[i + 1] = sum;
            pref[i + 1] = (pref[i] + dp[i + 1]) % MOD;
        }

        return (int) dp[n];
    }
}
