class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> window = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // If duplicate found in the current window
            if (window.contains(nums[i])) {
                return true;
            }

            // Add current element into the window
            window.add(nums[i]);

            // Maintain sliding window size = k
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }
        return false;
    }
}
