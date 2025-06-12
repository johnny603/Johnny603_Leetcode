class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int sol = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int next = (i + 1) % nums.length; // circular array
            sol = Math.abs(nums[i] - nums[next]);
            // get the max value of sol
            max = Math.max(max, sol);
        }
        return max;           
    }
}