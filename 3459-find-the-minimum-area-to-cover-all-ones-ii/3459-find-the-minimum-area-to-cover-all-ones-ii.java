class Solution {
    int rows, cols;

    // Helper: compute bounding box area of all 1's in a subgrid
    private int boundingBoxArea(int[][] grid, int r1, int r2, int c1, int c2) {
        int minRow = r2, maxRow = r1 - 1;
        int minCol = c2, maxCol = c1 - 1;

        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                if (grid[r][c] == 1) {
                    if (r < minRow) minRow = r;
                    if (r > maxRow) maxRow = r;
                    if (c < minCol) minCol = c;
                    if (c > maxCol) maxCol = c;
                }
            }
        }

        // no 1’s in this subgrid
        if (maxRow < minRow || maxCol < minCol) return 0;

        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }

    public int minimumSum(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int ans = Integer.MAX_VALUE;

        // --- 3 vertical strips ---
        for (int c1 = 0; c1 < cols - 2; c1++) {
            for (int c2 = c1 + 1; c2 < cols - 1; c2++) {
                int a1 = boundingBoxArea(grid, 0, rows - 1, 0, c1);
                int a2 = boundingBoxArea(grid, 0, rows - 1, c1 + 1, c2);
                int a3 = boundingBoxArea(grid, 0, rows - 1, c2 + 1, cols - 1);
                if (a1 > 0 && a2 > 0 && a3 > 0) ans = Math.min(ans, a1 + a2 + a3);
            }
        }

        // --- 3 horizontal strips ---
        for (int r1 = 0; r1 < rows - 2; r1++) {
            for (int r2 = r1 + 1; r2 < rows - 1; r2++) {
                int a1 = boundingBoxArea(grid, 0, r1, 0, cols - 1);
                int a2 = boundingBoxArea(grid, r1 + 1, r2, 0, cols - 1);
                int a3 = boundingBoxArea(grid, r2 + 1, rows - 1, 0, cols - 1);
                if (a1 > 0 && a2 > 0 && a3 > 0) ans = Math.min(ans, a1 + a2 + a3);
            }
        }

        // --- 2 vertical + 1 horizontal ---
        for (int c = 0; c < cols - 1; c++) {
            for (int r = 0; r < rows - 1; r++) {
                // Left split horizontally
                int a1 = boundingBoxArea(grid, 0, r, 0, c);
                int a2 = boundingBoxArea(grid, r + 1, rows - 1, 0, c);
                int a3 = boundingBoxArea(grid, 0, rows - 1, c + 1, cols - 1);
                if (a1 > 0 && a2 > 0 && a3 > 0) ans = Math.min(ans, a1 + a2 + a3);

                // Right split horizontally
                a1 = boundingBoxArea(grid, 0, r, c + 1, cols - 1);
                a2 = boundingBoxArea(grid, r + 1, rows - 1, c + 1, cols - 1);
                a3 = boundingBoxArea(grid, 0, rows - 1, 0, c);
                if (a1 > 0 && a2 > 0 && a3 > 0) ans = Math.min(ans, a1 + a2 + a3);
            }
        }

        // --- 2 horizontal + 1 vertical ---
        for (int r = 0; r < rows - 1; r++) {
            for (int c = 0; c < cols - 1; c++) {
                // Top split vertically
                int a1 = boundingBoxArea(grid, 0, r, 0, c);
                int a2 = boundingBoxArea(grid, 0, r, c + 1, cols - 1);
                int a3 = boundingBoxArea(grid, r + 1, rows - 1, 0, cols - 1);
                if (a1 > 0 && a2 > 0 && a3 > 0) ans = Math.min(ans, a1 + a2 + a3);

                // Bottom split vertically
                a1 = boundingBoxArea(grid, r + 1, rows - 1, 0, c);
                a2 = boundingBoxArea(grid, r + 1, rows - 1, c + 1, cols - 1);
                a3 = boundingBoxArea(grid, 0, r, 0, cols - 1);
                if (a1 > 0 && a2 > 0 && a3 > 0) ans = Math.min(ans, a1 + a2 + a3);
            }
        }

        return ans;
    }
}
