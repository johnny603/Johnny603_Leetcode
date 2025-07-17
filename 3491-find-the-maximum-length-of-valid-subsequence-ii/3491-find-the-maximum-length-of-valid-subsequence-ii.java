class Solution {
  public int maximumLength(int[] nums, int k) {
    int n = nums.length;
    int[][] dp = new int[n][k]; // dp[i][mod] = max length of subsequence ending at i with given mod
    int maxLen = 1;

    // Every element on its own is a valid subsequence of length 1
    for (int i = 0; i < n; ++i) {
      Arrays.fill(dp[i], 1);
    }

    // Compare all pairs (j, i) where j < i
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < i; ++j) {
        int mod = (nums[j] + nums[i]) % k;
        dp[i][mod] = Math.max(dp[i][mod], dp[j][mod] + 1);
        maxLen = Math.max(maxLen, dp[i][mod]);
      }
    }

    return maxLen;
  }
}
