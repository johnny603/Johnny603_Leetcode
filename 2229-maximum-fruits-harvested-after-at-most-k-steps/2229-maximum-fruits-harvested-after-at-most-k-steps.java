class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int maxFruits = 0;

        int left = 0, right = 0, total = 0;

        // Sliding window from left to right
        while (right < n) {
            total += fruits[right][1];

            // Compute minimum steps to go from startPos to fruits[left..right]
            while (left <= right && !canReach(fruits, left, right, startPos, k)) {
                total -= fruits[left][1];
                left++;
            }

            maxFruits = Math.max(maxFruits, total);
            right++;
        }

        return maxFruits;
    }

    // Determines if fruits in range [left..right] can be visited within k steps
    private boolean canReach(int[][] fruits, int left, int right, int startPos, int k) {
        int leftPos = fruits[left][0];
        int rightPos = fruits[right][0];

        // Two possible ways:
        // 1. Go to left end first, then to right
        // 2. Go to right end first, then to left
        int goLeftThenRight = Math.abs(startPos - leftPos) + (rightPos - leftPos);
        int goRightThenLeft = Math.abs(startPos - rightPos) + (rightPos - leftPos);

        return Math.min(goLeftThenRight, goRightThenLeft) <= k;
    }
}
