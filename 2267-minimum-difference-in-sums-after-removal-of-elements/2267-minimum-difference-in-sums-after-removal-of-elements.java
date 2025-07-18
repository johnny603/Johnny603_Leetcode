class Solution {
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        int len = nums.length;

        // Min heap for right part (we want to keep n smallest)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long[] rightMinSum = new long[len];
        long rightSum = 0;

        // Suffix from right to left
        for (int i = len - 1; i >= n; i--) {
            minHeap.add(nums[i]);
            rightSum += nums[i];

            if (minHeap.size() > n) {
                rightSum -= minHeap.poll();
            }

            if (minHeap.size() == n) {
                rightMinSum[i] = rightSum;
            } else {
                rightMinSum[i] = Long.MAX_VALUE; // not enough elements yet
            }
        }

        // Max heap for left part (we want to keep n largest)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long leftSum = 0;
        long minDiff = Long.MAX_VALUE;

        for (int i = 0; i < 2 * n; i++) {
            maxHeap.add(nums[i]);
            leftSum += nums[i];

            if (maxHeap.size() > n) {
                leftSum -= maxHeap.poll();
            }

            if (maxHeap.size() == n && rightMinSum[i + 1] != Long.MAX_VALUE) {
                long diff = leftSum - rightMinSum[i + 1];
                minDiff = Math.min(minDiff, diff);
            }
        }

        return minDiff;
    }
}
