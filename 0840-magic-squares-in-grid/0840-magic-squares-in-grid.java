class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int numMagic = 0;

        // Only iterate where a 3x3 grid fits
        for (int i = 0; i <= row - 3; i++) {
            for (int j = 0; j <= col - 3; j++) {
                if (isMagic(grid, i, j)) {
                    numMagic++;
                }
            }
        }
        return numMagic;
    }

    private boolean isMagic(int[][] grid, int r, int c) {
        // Center must be 5
        if (grid[r + 1][c + 1] != 5) return false;

        boolean[] seen = new boolean[10];

        // Check numbers 1–9 exactly once
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                int val = grid[i][j];
                if (val < 1 || val > 9 || seen[val]) {
                    return false;
                }
                seen[val] = true;
            }
        }

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (grid[r + i][c] + grid[r + i][c + 1] + grid[r + i][c + 2] != 15)
                return false;
            if (grid[r][c + i] + grid[r + 1][c + i] + grid[r + 2][c + i] != 15)
                return false;
        }

        // Check diagonals
        if (grid[r][c] + grid[r + 1][c + 1] + grid[r + 2][c + 2] != 15)
            return false;
        if (grid[r][c + 2] + grid[r + 1][c + 1] + grid[r + 2][c] != 15)
            return false;

        return true;
    }
}
