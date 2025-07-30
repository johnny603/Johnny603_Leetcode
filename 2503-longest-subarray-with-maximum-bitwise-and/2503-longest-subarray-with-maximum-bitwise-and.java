class Solution {
    public int longestSubarray(int[] nums) {
        int max_val = 0;

        // Find the maximum value in the array
        for (int num : nums) {
            if (num > max_val) {
                max_val = num;
            }
        }

        // Find the longest contiguous subarray of max_val
        int max_len = 0;
        int cur_len = 0;

        for (int num : nums) {
            if (num == max_val) {
                cur_len++;
                max_len = Math.max(max_len, cur_len);
            } else {
                cur_len = 0;
            }
        }

        return max_len;
    }
}
