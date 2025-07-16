class Solution {
  public int maximumLength(int[] nums) {
    // dp[lastParity][nextParity] stores the maximum length of a valid subsequence
    // ending in a number with 'lastParity' (0 for even, 1 for odd)
    // and expecting the next number to have 'nextParity' parity
    int[][] dp = new int[2][2];

    // Iterate over each number in the input array
    for (int num : nums) {
      int currentParity = num % 2;

      // We want to extend the longest subsequence that ends with the opposite parity
      // and now continues with this number (with currentParity).
      for (int nextParity = 0; nextParity < 2; ++nextParity) {
        // dp[currentParity][nextParity] represents the longest sequence that
        // ends with a number of currentParity and expects a number of nextParity.
        // It can be extended from dp[nextParity][currentParity] by adding current num.
        dp[currentParity][nextParity] = dp[nextParity][currentParity] + 1;
      }
    }

    // Flatten the dp matrix and find the maximum value
    return Arrays.stream(dp)
                 .flatMapToInt(Arrays::stream)
                 .max()
                 .getAsInt();
  }
}
