class Solution {
    public int returnToBoundaryCount(int[] nums) {
        int pos = 0;   // prefix sum / current position
        int sol = 0;

        for (int i = 0; i < nums.length; i++) {
            pos += nums[i];   // update prefix sum

            if (pos == 0) {
                sol++;
            }
        }

        return sol;
    }
}
