import java.util.*;

class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> diagMap = new HashMap<>();

        // Step 1: Group elements by diagonal key (i - j)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                diagMap.putIfAbsent(key, new ArrayList<>());
                diagMap.get(key).add(grid[i][j]);
            }
        }

        // Step 2: Sort each diagonal according to rules
        for (int key : diagMap.keySet()) {
            List<Integer> list = diagMap.get(key);
            if (key >= 0) {
                // bottom-left → sort descending
                list.sort(Collections.reverseOrder());
            } else {
                // top-right → sort ascending
                Collections.sort(list);
            }
        }

        // Step 3: Place back sorted values
        Map<Integer, Integer> indexMap = new HashMap<>(); // track index for each diag
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                int idx = indexMap.getOrDefault(key, 0);
                grid[i][j] = diagMap.get(key).get(idx);
                indexMap.put(key, idx + 1);
            }
        }

        return grid;
    }
}
