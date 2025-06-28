class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        // Pair each element with its original index
        // Sort the array based on values in descending order
        // to get the biggest sum, we typically want to add the biggest values
        // Pick the top k values, keeping track of their original indices
        // Sort the selected k indices to maintain the original order
        // Build and return the subsequence

        
        int n = nums.length;

        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }

        Arrays.sort(paired, (a, b) -> b[0] - a[0]);

        
        int[][] topK = Arrays.copyOfRange(paired, 0, k);

        Arrays.sort(topK, (a, b) -> a[1] - b[1]);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = topK[i][0];
        }

        return result;
    }
}