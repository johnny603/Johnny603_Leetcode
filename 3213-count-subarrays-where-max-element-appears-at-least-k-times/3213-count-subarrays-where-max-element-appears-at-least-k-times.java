class Solution {
    public long countSubarrays(int[] nums, int k) {
        long result = 0;

        int left = 0;
        int right = 0;
        int max = nums[0]; // Initialize max as the first element
        int count = 0;     // Count how many times max appears in the current window

        for (right = 0; right < nums.length; right++) {
            max = Math.max(max, nums[right]); // update the global maximum so far
        }

        // Reset variables for sliding window
        left = 0;
        count = 0;

        for (right = 0; right < nums.length; right++) {
            if (nums[right] == max) {
                count++; // found another occurrence of max
            }

            // shrink window from the left if count >= k
            while (count >= k) {
                if (nums[left] == max) {
                    count--; // remove occurrence from window
                }
                left++;
            }

            // when we shrink, it means subarrays ending at right are valid
            result += left; // add number of valid subarrays ending at right
        }

        return result;
    }
}
