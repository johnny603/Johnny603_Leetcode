class Solution {
    public int specialTriplets(int[] nums) {
        long mod = 1000000007L;
        int n = nums.length;

        Map<Integer, Long> right = new HashMap<>();
        Map<Integer, Long> left = new HashMap<>();

        // Initialize right counts
        for (int x : nums) {
            right.put(x, right.getOrDefault(x, 0L) + 1);
        }

        long answer = 0;

        for (int j = 0; j < n; j++) {
            int mid = nums[j];
            right.put(mid, right.get(mid) - 1); // remove from right

            int target = mid * 2;

            long leftCount = left.getOrDefault(target, 0L);
            long rightCount = right.getOrDefault(target, 0L);

            answer = (answer + (leftCount * rightCount) % mod) % mod;

            // Add current mid to left map
            left.put(mid, left.getOrDefault(mid, 0L) + 1);
        }

        return (int) answer;
    }
}
