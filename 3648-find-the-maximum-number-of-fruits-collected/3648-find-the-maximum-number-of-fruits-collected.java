public class Solution {

    // Directions for Player 2: moves diagonally or vertically down
    private static final int[][] directionsP2 = {{1, -1}, {1, 0}, {1, 1}};

    // Directions for Player 3: moves diagonally or horizontally right
    private static final int[][] directionsP3 = {{-1, 1}, {0, 1}, {1, 1}};

    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;

        // Player 1: Collect fruits along the main diagonal (top-left to bottom-right)
        int player1Total = 0;
        for (int i = 0; i < n; i++) {
            player1Total += fruits[i][i];
        }

        // Player 2: Starts at (0, n-1), tries to reach (n-1, n-1) avoiding diagonal path
        int[][] dpPlayer2 = new int[n][n];
        for (int[] row : dpPlayer2) Arrays.fill(row, -1);
        dpPlayer2[0][n - 1] = fruits[0][n - 1];

        for (int row = 0; row < n; row++) {
            for (int col = row + 1; col < n; col++) {
                if (dpPlayer2[row][col] == -1) continue;

                for (int[] dir : directionsP2) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    // Skip out-of-bounds or crossing the diagonal
                    if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n || nextCol <= nextRow)
                        continue;

                    dpPlayer2[nextRow][nextCol] = Math.max(dpPlayer2[nextRow][nextCol],
                                                           dpPlayer2[row][col] + fruits[nextRow][nextCol]);
                }
            }
        }

        // Handle bottom-right corner for Player 2
        dpPlayer2[n - 1][n - 1] = Math.max(dpPlayer2[n - 2][n - 1], dpPlayer2[n - 2][n - 2]);

        // Player 3: Starts at (n-1, 0), tries to reach (n-1, n-1) avoiding diagonal path
        int[][] dpPlayer3 = new int[n][n];
        for (int[] row : dpPlayer3) Arrays.fill(row, -1);
        dpPlayer3[n - 1][0] = fruits[n - 1][0];

        for (int col = 0; col < n; col++) {
            for (int row = col + 1; row < n; row++) {
                if (dpPlayer3[row][col] == -1) continue;

                for (int[] dir : directionsP3) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    // Skip out-of-bounds or crossing the diagonal
                    if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n || nextRow <= nextCol)
                        continue;

                    dpPlayer3[nextRow][nextCol] = Math.max(dpPlayer3[nextRow][nextCol],
                                                           dpPlayer3[row][col] + fruits[nextRow][nextCol]);
                }
            }
        }

        // Handle bottom-right corner for Player 3
        dpPlayer3[n - 1][n - 1] = Math.max(dpPlayer3[n - 1][n - 2], dpPlayer3[n - 2][n - 2]);

        // Total fruits collected by all three players
        return player1Total + dpPlayer2[n - 1][n - 1] + dpPlayer3[n - 1][n - 1];
    }
}
