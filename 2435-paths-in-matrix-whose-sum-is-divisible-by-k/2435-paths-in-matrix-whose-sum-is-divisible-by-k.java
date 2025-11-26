class Solution {

    // --- Grid and Parameters ---
    private int totalRows;
    private int totalCols;
    private int k;                 // Divisor for checking path sum
    private int[][] matrix;        // Input grid values

    private static final int MOD = 1_000_000_007;

    // Memoization: memo[r][c][remainder] = number of valid paths
    private int[][][] memo;

    /**
     * Returns number of paths from (0,0) → (rows-1, cols-1)
     * such that the sum along the path is divisible by k.
     */
    public int numberOfPaths(int[][] grid, int k) {
        this.matrix = grid;
        this.k = k;
        this.totalRows = grid.length;
        this.totalCols = grid[0].length;

        // Create memo table. Each state initially -1 (uncomputed).
        memo = new int[totalRows][totalCols][k];
        for (int[][] layer : memo) {
            for (int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }

        // Start DFS from the top-left with sum % k = 0
        return countPaths(0, 0, 0);
    }

    /**
     * DFS + Memo:
     * Counts valid paths starting at (row, col) given current remainder.
     */
    private int countPaths(int row, int col, int remainder) {

        // 1. Out-of-bound guard
        if (row >= totalRows || col >= totalCols) {
            return 0;
        }

        // 2. Update remainder with current cell
        int newRemainder = (remainder + matrix[row][col]) % k;

        // 3. If already computed, return cached result
        if (memo[row][col][newRemainder] != -1) {
            return memo[row][col][newRemainder];
        }

        // 4. Base case: last cell
        if (row == totalRows - 1 && col == totalCols - 1) {
            return (newRemainder == 0) ? 1 : 0;
        }

        // 5. Recurse: move Down and Right
        long ways =
            (long) countPaths(row + 1, col, newRemainder) +
            (long) countPaths(row, col + 1, newRemainder);

        // Apply modulo
        ways %= MOD;

        // 6. Store result in memo table
        memo[row][col][newRemainder] = (int) ways;

        return memo[row][col][newRemainder];
    }
}
