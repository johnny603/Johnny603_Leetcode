class Solution {
    public int maxSumDivThree(int[] nums) {
        // dp[r] = maximum sum we can make with remainder r when divided by 3
        int[] dp = new int[3];
        dp[1] = Integer.MIN_VALUE; // impossible state initially
        dp[2] = Integer.MIN_VALUE;

        for (int num : nums) {
            int[] next = dp.clone(); // clone current dp state

            for (int r = 0; r < 3; r++) {
                int newR = (r + num) % 3;
                next[newR] = Math.max(next[newR], dp[r] + num);
            }

            dp = next;
        }

        // dp[0] holds the maximum sum divisible by 3
        return dp[0];
    }
}
