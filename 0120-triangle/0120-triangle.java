/*
Copy the last row of the triangle into dp.

Move upward row by row:

For each element, add its value to the minimum of the two children directly below it.

The result builds up until dp[0], which holds the minimum path sum starting from the top.
*/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // Start with the last row
        int[] dp = new int[n];
        
        // Copy last row into dp
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        // Bottom-up DP
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }

        // dp[0] holds the min path sum
        return dp[0];
    }
}
