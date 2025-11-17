class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int prev = -1; // index of the previous 1

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // if we have seen a previous 1, check distance
                if (prev != -1 && i - prev - 1 < k) {
                    return false;
                }
                prev = i; // update previous 1 index
            }
        }
        return true;
    }
}
