class Solution {
    public int countPartitions(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0; // no partition possible

        int sol = 0;
        int total = 0;

        for (int x : nums) total += x;

        int left = 0;

        // partition AFTER index i, so i goes up to n-2
        for (int i = 0; i < n - 1; i++) {
            left += nums[i];
            int right = total - left;

            // difference even → both sums share same parity
            if ((left % 2 + 2) % 2 == (right % 2 + 2) % 2) {
                sol++;
            }
        }

        return sol;
    }
}
