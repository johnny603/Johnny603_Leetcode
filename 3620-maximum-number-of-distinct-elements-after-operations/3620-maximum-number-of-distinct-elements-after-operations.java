class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        long lastUsed = Long.MIN_VALUE; // use long to prevent overflow
        int distinct = 0;

        for (int num : nums) {
            long low = (long) num - k;
            long high = (long) num + k;
            
            // The smallest value we can assign that’s greater than lastUsed
            long newVal = Math.max(low, lastUsed + 1);

            // If newVal is still within range, we can use it
            if (newVal <= high) {
                distinct++;
                lastUsed = newVal;
            }
        }

        return distinct;
    }
}
