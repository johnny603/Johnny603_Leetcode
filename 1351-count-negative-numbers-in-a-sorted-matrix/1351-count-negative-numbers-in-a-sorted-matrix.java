class Solution {
    public int countNegatives(int[][] grid) {
        int negatives = 0;
        for (int i = 0; i < grid.length; i++) { // row
            for (int j = 0; j < grid[i].length; j++) { // col
                if (grid[i][j] < 0) {
                    negatives++;
                }
            }
        }
        return negatives;    
    }
}