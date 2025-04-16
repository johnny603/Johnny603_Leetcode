class Solution {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        long result = 0;
        long pairCount = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            int val = nums[right];
            int freq = freqMap.getOrDefault(val, 0);

            // Adding this element contributes 'freq' new pairs
            pairCount += freq;
            freqMap.put(val, freq + 1);

            // Shrink window from the left if enough pairs
            while (pairCount >= k) {
                // Count all good subarrays starting at left
                result += n - right;

                // Remove nums[left] from window
                int leftVal = nums[left];
                int leftFreq = freqMap.get(leftVal);
                pairCount -= (leftFreq - 1); // removing one instance decreases pairs by (freq - 1)
                freqMap.put(leftVal, leftFreq - 1);
                left++;
            }
        }

        return result;
    }
}
