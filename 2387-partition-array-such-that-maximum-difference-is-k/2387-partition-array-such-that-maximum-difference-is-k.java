class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        // use sliding window tech
        int i = 0;
        int j = 0;
        int count = 0;

        while (i < nums.length && j < nums.length) {
            if (nums[j] - nums[i] <= k) { // diff between the maximum and minimum values in each subsequence is at most k
                j++; // increase size of window
            } else {
                i = j;
                count++; // reset window and count the subsequence
            }
        }
        return count + 1;
    }
}