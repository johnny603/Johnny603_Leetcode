class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Iterate through each index
        for (int i = 0; i < n; i++) {

            // If value is 0, result is 0 directly
            if (nums[i] == 0) {
                result[i] = 0;
                continue;
            }

            // Compute circular landing index
            // (i + nums[i]) may go out of bounds or be negative
            // so we normalize using modulo
            int target = (i + nums[i]) % n;

            // Java negative modulo fix
            if (target < 0) {
                target += n;
            }

            // Store the value at the landing index
            result[i] = nums[target];
        }

        return result;
    }
}
