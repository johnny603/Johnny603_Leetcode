// Everything is sorted so we make a staircase of negatives and positives
// Ex:
/*
+ + + + -
+ + + - -
+ + - - -
+ - - - -
*/

class Solution {
    public int countNegatives(int[][] grid) {
        int negatives = 0;
        for (int i = 0; i < grid.length; i++) { // row
            for (int j = 0; j < grid[0].length; j++) { // col
                if (grid[i][j] < 0) {
                    negatives += grid[0].length - j; // if one is negative the rest in the right are negative in the row
                    break; // move to next row
                }
            }
        }
        return negatives;    
    }
}