class Solution {
    public int countSubarrays(int[] nums) {
        int result = 0;
        // Loop from index 0 to nums.length - 3
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            int second = nums[i + 1];
            int third = nums[i + 2];
            // Correct condition: check if first + third equals half of second
            if (first + third == second / 2.0) {  // We use 2.0 for floating-point division
                result++;
            }
        }
        return result;
    }
}
