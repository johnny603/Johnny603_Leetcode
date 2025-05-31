class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0}); // {square, moves}
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int square = current[0];
            int moves = current[1];

            if (square == n * n) return moves;

            for (int i = 1; i <= 6; i++) {
                int nextSquare = square + i;
                if (nextSquare > n * n) continue;

                int[] coords = getCoordinates(nextSquare, n);
                int row = coords[0], col = coords[1];

                if (board[row][col] != -1) {
                    nextSquare = board[row][col];
                }

                if (!visited[nextSquare]) {
                    visited[nextSquare] = true;
                    queue.offer(new int[]{nextSquare, moves + 1});
                }
            }
        }
        return -1;
    }

    // Helper to convert 1D square to 2D boustrophedon coordinates
    private int[] getCoordinates(int square, int n) {
        int r = n - 1 - (square - 1) / n;
        int c = (square - 1) % n;
        if (((n - 1 - r) % 2) == 1) {
            c = n - 1 - c;
        }
        return new int[]{r, c};
    }
}
