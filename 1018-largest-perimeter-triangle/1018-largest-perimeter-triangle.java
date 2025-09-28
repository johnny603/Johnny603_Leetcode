// Idea: sort array from smallest to largest
// Then pick the last three
// If the array is empty or has a size less than three, return 0

class Solution {
    public int largestPerimeter(int[] nums) {
        // If less than 3 sides, can't form a triangle
        if (nums == null || nums.length < 3) {
            return 0;
        }

        // Sort ascending
        Arrays.sort(nums);

        // Start from the largest side and check triples
        for (int i = nums.length - 1; i >= 2; i--) {
            // Check triangle inequality: a + b > c
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }

        // If no valid triangle
        return 0;
    }
}