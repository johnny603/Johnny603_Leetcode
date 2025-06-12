class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int sol = 0;
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n; // circular array
            sol = Math.abs(nums[i] - nums[next]);
            // get the max value of sol
            max = Math.max(max, sol);
        }
        return max;           
    }
}