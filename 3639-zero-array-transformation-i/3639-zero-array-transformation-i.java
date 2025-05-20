class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] count = new int[n + 1]; // difference array approach

        // Step 1: Mark the increments and decrements
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            count[l] += 1;
            if (r + 1 < n) {
                count[r + 1] -= 1;
            }
        }

        // Step 2: Build the prefix sum to get actual counts
        for (int i = 1; i < n; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Check if each element can be reduced to 0
        for (int i = 0; i < n; i++) {
            if (count[i] < nums[i]) {
                return false;
            }
        }

        return true;
    }
}
