class Solution {
    static final int INF = Integer.MAX_VALUE / 2;

    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // f[t][i][j]
        int[][][] f = new int[k + 1][m][n];
        for (int t = 0; t <= k; t++)
            for (int i = 0; i < m; i++)
                Arrays.fill(f[t][i], INF);

        f[0][0][0] = 0;

        // Base DP (no teleport)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0)
                    f[0][i][j] = Math.min(f[0][i][j], f[0][i - 1][j] + grid[i][j]);
                if (j > 0)
                    f[0][i][j] = Math.min(f[0][i][j], f[0][i][j - 1] + grid[i][j]);
            }
        }

        // Group cells by value
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(grid[i][j], x -> new ArrayList<>())
                   .add(new int[]{i, j});
            }
        }

        // Sort keys descending
        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort(Collections.reverseOrder());

        // Teleport layers
        for (int t = 1; t <= k; t++) {
            int mn = INF;

            for (int key : keys) {
                for (int[] pos : map.get(key)) {
                    mn = Math.min(mn, f[t - 1][pos[0]][pos[1]]);
                }
                for (int[] pos : map.get(key)) {
                    f[t][pos[0]][pos[1]] = mn;
                }
            }

            // Normal DP transitions
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0)
                        f[t][i][j] = Math.min(f[t][i][j], f[t][i - 1][j] + grid[i][j]);
                    if (j > 0)
                        f[t][i][j] = Math.min(f[t][i][j], f[t][i][j - 1] + grid[i][j]);
                }
            }
        }

        int ans = INF;
        for (int t = 0; t <= k; t++)
            ans = Math.min(ans, f[t][m - 1][n - 1]);

        return ans;
    }
}
