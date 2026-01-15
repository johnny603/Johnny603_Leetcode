class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        /*
         * The grid has (n + 2) horizontal bars and (m + 2) vertical bars.
         * Cells are the spaces BETWEEN bars, and each cell is 1x1.
         *
         * We are allowed to remove only the bars listed in hBars and vBars.
         * Removing consecutive bars merges the cells between them.
         */

        // Sort the removable bars so we can find consecutive bar sequences
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        /*
         * longestConsecutive(...) returns the maximum number of
         * consecutive bars that can be removed.
         *
         * If we remove k consecutive bars, we merge (k + 1) unit cells.
         * Therefore, the side length of the hole is (k + 1).
         */
        int maxH = longestConsecutive(hBars) + 1; // max height in cells
        int maxV = longestConsecutive(vBars) + 1; // max width in cells

        /*
         * A square hole must have equal height and width.
         * The largest possible square is limited by the smaller dimension.
         */
        int side = Math.min(maxH, maxV);

        // Area of the square-shaped hole
        return side * side;
    }

    private int longestConsecutive(int[] arr) {
        /*
         * Finds the longest sequence of consecutive integers in arr.
         * Each sequence represents removable bars that are adjacent in the grid.
         */
        int max = 1;   // longest streak found
        int curr = 1;  // current streak length

        for (int i = 1; i < arr.length; i++) {
            // Check if the current bar index is consecutive to the previous one
            if (arr[i] == arr[i - 1] + 1) {
                curr++;
            } else {
                // Break in consecutiveness → reset streak
                curr = 1;
            }
            max = Math.max(max, curr);
        }
        return max;
    }
}
