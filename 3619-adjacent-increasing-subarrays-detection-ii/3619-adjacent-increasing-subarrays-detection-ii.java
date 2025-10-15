class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int maxLength = 0;
        int previousLength = 0;
        int currentLength = 0;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
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