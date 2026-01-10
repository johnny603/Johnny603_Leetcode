class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // Base cases: if one string is empty, delete all chars from the other
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = dp[i + 1][n] + s1.charAt(i);
        }
        for (int j = n - 1; j >= 0; j--) {
            dp[m][j] = dp[m][j + 1] + s2.charAt(j);
        }
        
        // Fill the DP table
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1]; // characters match
                } else {
                    dp[i][j] = Math.min(
                        s1.charAt(i) + dp[i + 1][j], // delete s1[i]
                        s2.charAt(j) + dp[i][j + 1]  // delete s2[j]
                    );
                }
            }
        }
        
        return dp[0][0];
    }
}
