// Brute-force approach
/*
class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0)
            return 0;

        int m = heightMap.length;
        int n = heightMap[0].length;
        int waterBlock = 0;

        // Loop over the entire grid excluding the borders (no water trapped at border)
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {

                // Find max height on the left of current cell
                int maxLeft = heightMap[i][j];
                for (int k = 0; k < j; k++) {
                    maxLeft = Math.max(maxLeft, heightMap[i][k]);
                }

                // Find max height on the right
                int maxRight = heightMap[i][j];
                for (int k = j + 1; k < n; k++) {
                    maxRight = Math.max(maxRight, heightMap[i][k]);
                }

                // Find max height on the top
                int maxTop = heightMap[i][j];
                for (int k = 0; k < i; k++) {
                    maxTop = Math.max(maxTop, heightMap[k][j]);
                }

                // Find max height on the bottom
                int maxBottom = heightMap[i][j];
                for (int k = i + 1; k < m; k++) {
                    maxBottom = Math.max(maxBottom, heightMap[k][j]);
                }

                // The water level at (i, j) is the minimum of the 4 directions
                int minHeight = Math.min(Math.min(maxLeft, maxRight), Math.min(maxTop, maxBottom));

                // Water can only be trapped if minHeight > current height
                if (minHeight > heightMap[i][j]) {
                    waterBlock += minHeight - heightMap[i][j];
                }
            }
        }

        return waterBlock;
    }
}
*/

// Optimized approach using a heap
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        if (m <= 2 || n <= 2) return 0; // No room to trap water

        boolean[][] visited = new boolean[m][n];

        // Min-heap (PriorityQueue) to always process the lowest height first
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.height - b.height);

        // Add all the border cells to the heap
        for (int i = 0; i < m; i++) {
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        for (int j = 1; j < n - 1; j++) {
            pq.offer(new Cell(0, j, heightMap[0][j]));
            pq.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }

        int totalWater = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!pq.isEmpty()) {
            Cell cell = pq.poll();

            for (int[] dir : dirs) {
                int x = cell.row + dir[0];
                int y = cell.col + dir[1];

                // Skip out-of-bounds or already visited cells
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) continue;

                visited[x][y] = true;

                // If neighbor is lower, water can be trapped
                int neighborHeight = heightMap[x][y];
                int water = Math.max(0, cell.height - neighborHeight);
                totalWater += water;

                // Push the neighbor with updated height (wall)
                pq.offer(new Cell(x, y, Math.max(neighborHeight, cell.height)));
            }
        }

        return totalWater;
    }

    // Helper class for cells
    static class Cell {
        int row, col, height;
        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
}

