class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];

        // Count trailing zeros for each row
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--) {
                count++;
            }
            zeros[i] = count;
        }

        int swaps = 0;

        // Greedy placement
        for (int i = 0; i < n; i++) {
            int needed = n - 1 - i;
            int j = i;

            // Find first valid row
            while (j < n && zeros[j] < needed) {
                j++;
            }

            if (j == n) return -1;

            // Bubble up
            while (j > i) {
                int temp = zeros[j];
                zeros[j] = zeros[j - 1];
                zeros[j - 1] = temp;
                j--;
                swaps++;
            }
        }

        return swaps;
    }
}