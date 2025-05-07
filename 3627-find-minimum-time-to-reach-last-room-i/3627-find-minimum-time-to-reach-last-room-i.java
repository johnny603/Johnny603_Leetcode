public class Solution {
    public int minTimeToReach(int[][] moveTime) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // heap stores elements as {currentTime, row, col}
        heap.add(new int[]{0, 0, 0});

        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            int currentTime = current[0];
            int row = current[1];
            int col = current[2];

            String key = row + "," + col;
            if (distances.containsKey(key)) {
                continue;
            }

            distances.put(key, currentTime);

            // Directions: right, left, down, up
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow < 0 || newRow >= moveTime.length || newCol < 0 || newCol >= moveTime[0].length) {
                    continue;
                }

                int newTime = currentTime;
                if (currentTime >= moveTime[newRow][newCol]) {
                    newTime += 1;
                } else {
                    newTime = moveTime[newRow][newCol] + 1;
                }

                heap.add(new int[]{newTime, newRow, newCol});
            }
        }

        int endRow = moveTime.length - 1;
        int endCol = moveTime[0].length - 1;
        String endKey = endRow + "," + endCol;
        return distances.get(endKey);
    }
}
