class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        int left = 0;
        int best = 1; // largest balanced window

        for (int right = 0; right < n; right++) {
            // shrink window until valid
            while ((long) nums[right] > (long) nums[left] * k) {
                left++;
            }

            best = Math.max(best, right - left + 1);
        }

        return n - best;
    }
}
