class Solution {
    public long countSubarrays(int[] nums, long k) {
        long result = 0;
        long sum = 0;
        int left = 0;
        
        // Iterate through the array with the right pointer
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];  // Add the current element to the sum
            
            // Shrink the window from the left if the score exceeds k
            while ((sum * (right - left + 1)) >= k) {
                sum -= nums[left++];  // Move the left pointer and reduce the sum
            }
            
            // All subarrays from left to right are valid
            result += (right - left + 1);
        }
        
        return result;
    }
}
