class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int longest = 0;

        // Try every possible starting index of a subarray
        for (int start = 0; start < n; start++) {

            // Track distinct numbers we’ve seen in this subarray
            Set<Integer> seen = new HashSet<>();

            // parityCount[0] = number of distinct even values
            // parityCount[1] = number of distinct odd values
            int[] parityCount = new int[2];

            // Expand subarray ending at 'end'
            for (int end = start; end < n; end++) {

                int value = nums[end];

                // If value is new to this subarray, update parity count
                if (seen.add(value)) {
                    int parity = value & 1; // 0 = even, 1 = odd
                    parityCount[parity]++;
                }

                // Check if balanced
                if (parityCount[0] == parityCount[1]) {
                    longest = Math.max(longest, end - start + 1);
                }
            }
        }

        return longest;
    }
}
