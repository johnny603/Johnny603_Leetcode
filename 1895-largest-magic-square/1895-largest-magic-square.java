class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxSize = 1; // smallest magic square is 1x1

        // Try every possible top-left corner
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Try every possible size from 2 to min(m-i, n-j)
                for (int k = 2; k <= Math.min(m - i, n - j); k++) {
                    if (isMagic(grid, i, j, k)) {
                        maxSize = Math.max(maxSize, k);
                    }
                }
            }
        }
        return maxSize;
    }

    private boolean isMagic(int[][] grid, int r, int c, int size) {
        int targetSum = 0;
        // Sum of first row
        for (int j = c; j < c + size; j++) {
            targetSum += grid[r][j];
        }

        // Check all rows
        for (int i = r; i < r + size; i++) {
            int rowSum = 0;
            for (int j = c; j < c + size; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum != targetSum) return false;
        }

        // Check all columns
        for (int j = c; j < c + size; j++) {
            int colSum = 0;
            for (int i = r; i < r + size; i++) {
                colSum += grid[i][j];
            }
            if (colSum != targetSum) return false;
        }

        // Check main diagonal
        int diag1 = 0;
        for (int i = 0; i < size; i++) {
            diag1 += grid[r + i][c + i];
        }
        if (diag1 != targetSum) return false;

        // Check anti-diagonal
        int diag2 = 0;
        for (int i = 0; i < size; i++) {
            diag2 += grid[r + i][c + size - 1 - i];
        }
        if (diag2 != targetSum) return false;

        return true;
    }
}
