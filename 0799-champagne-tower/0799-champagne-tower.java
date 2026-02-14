class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        // dp[r][c] = total champagne that reaches glass (r, c)
        double[][] dp = new double[101][101];
        
        // base case: all champagne starts in the top glass
        dp[0][0] = poured;
        
        // simulate flow row by row
        for (int r = 0; r < query_row; r++) {
            for (int c = 0; c <= r; c++) {
                // if glass overflows, distribute excess
                if (dp[r][c] > 1) {
                    double overflow = dp[r][c] - 1;
                    dp[r+1][c] += overflow / 2.0;
                    dp[r+1][c+1] += overflow / 2.0;
                }
            }
        }
        
        // glass can't hold more than 1 cup
        return Math.min(1.0, dp[query_row][query_glass]);
    }
}
