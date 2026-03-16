class Solution {
    public int[] getBiggestThree(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        /*
         Diagonal prefix sums

         downRightPrefix[r][c]
         stores the sum along the diagonal ↘ ending at (r-1,c-1)

         downLeftPrefix[r][c]
         stores the sum along the diagonal ↙ ending at (r-1,c-1)

         We pad the arrays slightly to avoid boundary checks.
        */
        int[][] downRightPrefix = new int[rows + 1][cols + 2];
        int[][] downLeftPrefix = new int[rows + 1][cols + 2];

        // Build prefix sums for both diagonals
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {

                // ↘ diagonal
                downRightPrefix[r][c] =
                        downRightPrefix[r - 1][c - 1] + grid[r - 1][c - 1];

                // ↙ diagonal
                downLeftPrefix[r][c] =
                        downLeftPrefix[r - 1][c + 1] + grid[r - 1][c - 1];
            }
        }

        /*
         TreeSet keeps sums:
         - unique
         - automatically sorted
         */
        TreeSet<Integer> topSums = new TreeSet<>();

        // Try every cell as a rhombus center
        for (int centerRow = 1; centerRow <= rows; centerRow++) {
            for (int centerCol = 1; centerCol <= cols; centerCol++) {

                // Radius 0 rhombus = just the cell itself
                topSums.add(grid[centerRow - 1][centerCol - 1]);

                /*
                 Maximum rhombus radius possible from this center
                 without leaving the grid.
                */
                int maxRadius = Math.min(
                        Math.min(centerRow - 1, rows - centerRow),
                        Math.min(centerCol - 1, cols - centerCol)
                );

                // Expand rhombus size
                for (int radius = 1; radius <= maxRadius; radius++) {

                    /*
                     Each rhombus border consists of 4 diagonal edges.
                     Using prefix sums we can compute each edge in O(1).
                    */

                    // Edge: top → right
                    int topRightEdge =
                            downRightPrefix[centerRow + radius][centerCol]
                          - downRightPrefix[centerRow][centerCol - radius];

                    // Edge: right → bottom
                    int bottomRightEdge =
                            downRightPrefix[centerRow][centerCol + radius]
                          - downRightPrefix[centerRow - radius][centerCol];

                    // Edge: top → left
                    int topLeftEdge =
                            downLeftPrefix[centerRow][centerCol - radius]
                          - downLeftPrefix[centerRow - radius][centerCol];

                    // Edge: left → bottom
                    int bottomLeftEdge =
                            downLeftPrefix[centerRow + radius][centerCol]
                          - downLeftPrefix[centerRow][centerCol + radius];

                    /*
                     Combine all edges.

                     One corner gets counted twice and another isn't counted,
                     so we correct it manually.
                    */
                    int rhombusSum =
                            topRightEdge
                          + bottomRightEdge
                          + topLeftEdge
                          + bottomLeftEdge
                          - grid[centerRow + radius - 1][centerCol - 1]  // remove duplicate bottom corner
                          + grid[centerRow - radius - 1][centerCol - 1]; // add missing top corner

                    topSums.add(rhombusSum);
                }

                // Keep only the 3 largest sums
                while (topSums.size() > 3) {
                    topSums.pollFirst(); // remove smallest
                }
            }
        }

        // Convert TreeSet to result array (largest → smallest)
        int[] result = new int[topSums.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = topSums.pollLast();
        }

        return result;
    }
}