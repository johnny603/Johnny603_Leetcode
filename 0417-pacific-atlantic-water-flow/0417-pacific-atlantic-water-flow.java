class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // DFS from all Pacific border cells
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, heights[i][0]);  // Left border
            dfs(heights, atlantic, i, n - 1, heights[i][n - 1]);  // Right border
        }

        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, heights[0][j]);  // Top border
            dfs(heights, atlantic, m - 1, j, heights[m - 1][j]);  // Bottom border
        }

        // Collect cells that can reach both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] visited, int r, int c, int prevHeight) {
        int m = heights.length, n = heights[0].length;
        // Base conditions
        if (r < 0 || c < 0 || r >= m || c >= n) return;
        if (visited[r][c]) return;
        if (heights[r][c] < prevHeight) return;

        visited[r][c] = true;
        // Explore 4 directions
        dfs(heights, visited, r + 1, c, heights[r][c]);
        dfs(heights, visited, r - 1, c, heights[r][c]);
        dfs(heights, visited, r, c + 1, heights[r][c]);
        dfs(heights, visited, r, c - 1, heights[r][c]);
    }
}
