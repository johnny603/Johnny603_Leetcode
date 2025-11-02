class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];

        // 1 = wall, 2 = guard, 3 = guarded

        // mark walls
        for (int[] w : walls) {
            grid[w[0]][w[1]] = 1;
        }

        // mark guards
        for (int[] g : guards) {
            grid[g[0]][g[1]] = 2;
        }

        // directions: up, down, left, right
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        // for each guard, mark visible cells
        for (int[] g : guards) {
            for (int[] d : dirs) {
                int x = g[0] + d[0];
                int y = g[1] + d[1];
                while (x >= 0 && x < m && y >= 0 && y < n) {
                    if (grid[x][y] == 1 || grid[x][y] == 2) break; // wall or guard stops vision
                    if (grid[x][y] == 0) grid[x][y] = 3; // mark guarded
                    x += d[0];
                    y += d[1];
                }
            }
        }

        // count unguarded empty cells
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) count++;
            }
        }

        return count;
    }
}
