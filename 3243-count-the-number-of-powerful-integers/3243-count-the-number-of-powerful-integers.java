class Solution {
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        return fn(String.valueOf(finish), limit, s) - fn(String.valueOf(start - 1), limit, s);
    }
    
    // Helper function to count powerful integers <= val
    private long fn(String val, int limit, String s) {
        int n = val.length() - s.length();
        if (n < 0) return 0; // If val is shorter than s, no powerful integers exist
        
        // Create DP array
        long[][] dp = new long[n + 1][2];
        
        // Base cases
        dp[n][0] = 1;
        dp[n][1] = (val.substring(n).compareTo(s) >= 0) ? 1 : 0;
        
        // Fill DP array
        for (int i = n - 1; i >= 0; i--) {
            // For unrestricted case (j=0)
            dp[i][0] = (1 + limit) * dp[i + 1][0];
            
            // For restricted case (j=1)
            int digit = val.charAt(i) - '0';
            if (digit <= limit) {
                dp[i][1] = digit * dp[i + 1][0] + dp[i + 1][1];
            } else {
                dp[i][1] = (1 + limit) * dp[i + 1][0];
            }
        }
        
        return dp[0][1];
    }
}