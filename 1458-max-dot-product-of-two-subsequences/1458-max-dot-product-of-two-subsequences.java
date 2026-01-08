class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];

        int NEG = Integer.MIN_VALUE / 2;

        // Initialize dp with very negative values
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = NEG;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int prod = nums1[i - 1] * nums2[j - 1];

                dp[i][j] = Math.max(
                    prod,                              // start new subsequence
                    dp[i - 1][j - 1] + prod             // extend previous
                );

                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]); // skip nums1
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]); // skip nums2
            }
        }

        return dp[n][m];
    }
}
