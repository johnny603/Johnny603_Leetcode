class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        boolean[][] visited = new boolean[n][m];

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // {current_time, row, col, move_count}
        heap.offer(new int[]{0, 0, 0, 0});

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int time = curr[0], row = curr[1], col = curr[2], move = curr[3];

            if (visited[row][col]) continue;
            visited[row][col] = true;

            if (row == n - 1 && col == m - 1) {
                return time;
            }

            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : directions) {
                int newRow = row + dir[0], newCol = col + dir[1];

                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m) continue;

                int timeToMove = (move % 2 == 1) ? 2 : 1;
                int newTime = time;
                if (time >= moveTime[newRow][newCol]) {
                    newTime += timeToMove;
                } else {
                    newTime = moveTime[newRow][newCol] + timeToMove;
                }

                heap.offer(new int[]{newTime, newRow, newCol, move + 1});
            }
        }

        return -1; // should not reach here in a valid grid
    }
}
