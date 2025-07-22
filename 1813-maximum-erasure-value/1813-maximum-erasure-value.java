public class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        // Set to store unique elements in the current window
        Set<Integer> seen = new HashSet<>();
        
        int left = 0;           // Left pointer for sliding window
        int currentSum = 0;     // Sum of elements in current window
        int maxSum = 0;         // Maximum sum found so far
        
        // Iterate with right pointer over nums array
        for (int right = 0; right < nums.length; right++) {
            // If nums[right] is already in the window (duplicate found)
            while (seen.contains(nums[right])) {
                // Remove nums[left] from the window
                seen.remove(nums[left]);
                currentSum -= nums[left];
                left++;  // Shrink window from the left side
            }
            
            // Add the current number to the window
            seen.add(nums[right]);
            currentSum += nums[right];
            
            // Update maxSum if current window sum is greater
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
}
