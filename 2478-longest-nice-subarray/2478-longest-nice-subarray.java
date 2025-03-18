class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int combined = 0;  // Combined bit representation of current window
        int maxLength = 1;  // Minimum nice subarray length is 1
        
        for (int right = 0; right < n; right++) {
            // Check if the current number has overlapping bits with our window
            while ((combined & nums[right]) != 0) {
                // Remove leftmost element from our window
                combined ^= nums[left];
                left++;
            }
            
            // Add current number to our window
            combined |= nums[right];
            
            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}