class Solution {
    public int minimumDifference(int[] nums, int k) {
        // Edge case
        if (k == 1) return 0;

        Arrays.sort(nums);

        int diff = Integer.MAX_VALUE;

        for (int i = 0; i <= nums.length - k; i++) {
            int currentDiff = nums[i + k - 1] - nums[i];
            diff = Math.min(diff, currentDiff);
        }

        return diff;
    }
}
