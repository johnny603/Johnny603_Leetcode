class Solution {
    public int minNumberOperations(int[] target) {
        int minOperations = target[0]; // Start with the first element
        
        // For each position, if target increases, we need more operations
        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1]) {
                minOperations += target[i] - target[i - 1];
            }
            // If target[i] <= target[i-1], no additional operations needed
        }
        
        return minOperations;
    }
}