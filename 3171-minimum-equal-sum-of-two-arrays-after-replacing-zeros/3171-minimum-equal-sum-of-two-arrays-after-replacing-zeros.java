class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0, sum2 = 0; // Sum of elements in each array
        int zero1 = 0, zero2 = 0; // Count of zeros in each array

        // Process nums1
        for (int num : nums1) {
            sum1 += num;
            if (num == 0) zero1++; // Count zero in nums1
        }

        // Process nums2
        for (int num : nums2) {
            sum2 += num;
            if (num == 0) zero2++; // Count zero in nums2
        }

        // Check impossible cases:
        // If nums1 has no zeros and its sum is less than the minimal possible sum of nums2 (after replacing its 0s with 1s)
        if (zero1 == 0 && sum1 < sum2 + zero2) return -1;

        // If nums2 has no zeros and its sum is less than the minimal possible sum of nums1 (after replacing its 0s with 1s)
        if (zero2 == 0 && sum2 < sum1 + zero1) return -1;

        // Return the maximum of (sum1 + minimal replacements) and (sum2 + minimal replacements)
        // Because we need to make both equal, and can only increase the array with zeros
        return Math.max(sum1 + zero1, sum2 + zero2);
    }
}
