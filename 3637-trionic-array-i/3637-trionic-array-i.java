class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;

        // strictly increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) i++;
        if (i == 0) return false; // no increasing part

        // strictly decreasing
        int p = i;
        while (i + 1 < n && nums[i] > nums[i + 1]) i++;
        if (i == p) return false; // no decreasing part

        // strictly increasing
        int q = i;
        while (i + 1 < n && nums[i] < nums[i + 1]) i++;
        if (i == q) return false; // no final increasing part

        // must end exactly at the last index
        return i == n - 1;
    }
}
