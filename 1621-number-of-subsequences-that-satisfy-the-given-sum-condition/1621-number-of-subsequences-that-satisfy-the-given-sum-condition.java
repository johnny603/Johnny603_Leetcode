
// keep track of number of sequences with a count variable
// pair each element with an index
// pick candidates for the condition
// condition: the sum of the minimum and maximum element on it is less or equal to target

class Solution {
    public int numSubseq(int[] nums, int target) {
        // Sort the array to efficiently access min and max of a subsequence
        Arrays.sort(nums);

        int mod = 1_000_000_007;
        int n = nums.length;
        int count = 0;

        // Precompute powers of 2 for subsequence combinations
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; i++) {
            power[i] = (power[i - 1] * 2) % mod;
        }

        // Use two pointers to find valid subsequences
        int left = 0, right = n - 1;
        while (left <= right) {
            // Check if the current pair (nums[left], nums[right]) satisfies the condition
            if (nums[left] + nums[right] <= target) {
                // If valid, all subsets between left and right are valid
                count = (count + power[right - left]) % mod;
                left++;
            } else {
                // If not valid, reduce the right pointer
                right--;
            }
        }

        return count;
    }
}
