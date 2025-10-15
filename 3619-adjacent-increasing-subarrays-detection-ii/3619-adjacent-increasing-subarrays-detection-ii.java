class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        // Variable to store the maximum length k of two adjacent strictly increasing subarrays
        int maxLength = 0;
      
        // Length of the previous strictly increasing subarray
        int previousLength = 0;
      
        // Length of the current strictly increasing subarray being processed
        int currentLength = 0;
      
        // Total number of elements in the list
        int n = nums.size();
      
        // Iterate through all elements in the list
        for (int i = 0; i < n; i++) {
            // Increment current subarray length
            currentLength++;
          
            // Check if we've reached the end of a strictly increasing subarray
            // This happens when we're at the last element OR the next element is not greater
            if (i == n - 1 || nums.get(i) >= nums.get(i + 1)) {
                // Update the maximum length k considering two cases:
                // 1. Split the current subarray into two equal parts (currentLength / 2)
                // 2. Use the previous and current subarrays (minimum of their lengths)
                maxLength = Math.max(maxLength, 
                                   Math.max(currentLength / 2, 
                                          Math.min(previousLength, currentLength)));
              
                // Current subarray becomes the previous for next iteration
                previousLength = currentLength;
              
                // Reset current subarray length for the next strictly increasing sequence
                currentLength = 0;
            }
        }
      
        return maxLength;
    }
}