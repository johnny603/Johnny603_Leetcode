class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();

        // dp[j] = longest valid sequence ending at column j
        int[] dp = new int[cols];

        // base case: each column alone is valid
        for (int j = 0; j < cols; j++) {
            dp[j] = 1;
        }

        // build dp
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < j; i++) {
                boolean valid = true;

                // check all rows
                for (int r = 0; r < rows; r++) {
                    if (strs[r].charAt(i) > strs[r].charAt(j)) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        // find maximum columns we can keep
        int maxKept = 0;
        for (int val : dp) {
            maxKept = Math.max(maxKept, val);
        }

        // minimum deletions
        return cols - maxKept;
    }
}
