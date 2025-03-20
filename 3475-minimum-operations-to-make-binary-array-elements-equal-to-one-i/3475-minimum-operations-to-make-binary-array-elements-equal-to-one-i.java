class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int operations = 0;
        int[] arr = nums.clone();
        
        // Greedy approach: For each position from the left, if it's 0, flip it
        for (int i = 0; i <= n - 3; i++) {
            if (arr[i] == 0) {
                // Flip i, i+1, i+2
                arr[i] ^= 1;
                arr[i+1] ^= 1;
                arr[i+2] ^= 1;
                operations++;
            }
        }
        
        // Check if the last two elements are 1
        if (arr[n-2] == 0 || arr[n-1] == 0) {
            return -1;  // Not possible to make all elements 1
        }
        
        return operations;
    }
}