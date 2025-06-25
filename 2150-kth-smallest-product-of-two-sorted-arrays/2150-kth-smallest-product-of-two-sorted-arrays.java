class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        // Step 1: Separate nums1 and nums2 into positive and reversed-negative arrays
        List<Long> neg1 = new ArrayList<>();
        List<Long> pos1 = new ArrayList<>();
        List<Long> neg2 = new ArrayList<>();
        List<Long> pos2 = new ArrayList<>();

        for (int x : nums1) {
            if (x < 0) neg1.add((long)(-x)); // flip sign
            else pos1.add((long)x);
        }
        Collections.reverse(neg1); // to maintain sorted order

        for (int x : nums2) {
            if (x < 0) neg2.add((long)(-x)); // flip sign
            else pos2.add((long)x);
        }
        Collections.reverse(neg2); // to maintain sorted order

        long totalNegativePairs = 1L * neg1.size() * pos2.size() + 1L * pos1.size() * neg2.size();
        int sign = 1;

        if (k > totalNegativePairs) {
            // kth product is positive
            k -= totalNegativePairs;
        } else {
            // kth product is negative
            k = totalNegativePairs - k + 1;
            sign = -1;
            // swap neg2 and pos2 for correct pairing
            List<Long> temp = neg2;
            neg2 = pos2;
            pos2 = temp;
        }

        long left = 0, right = (long)1e10;

        while (left < right) {
            long mid = (left + right) / 2;
            long count = countLEQ(neg1, neg2, mid) + countLEQ(pos1, pos2, mid);

            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return sign * left;
    }

    // Count how many products a * b <= maxProduct using two-pointer technique
    private long countLEQ(List<Long> A, List<Long> B, long maxProduct) {
        long count = 0;
        int j = B.size() - 1;

        for (long a : A) {
            while (j >= 0 && a * B.get(j) > maxProduct) {
                j--;
            }
            count += (j + 1);
        }

        return count;
    }
}
