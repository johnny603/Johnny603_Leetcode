class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        // Map to store the frequency of each number in the array
        Map<Integer, Integer> frequencyMap = new HashMap<>();
      
        // TreeMap to track range boundaries and their contributions
        // Used for sweep line algorithm to count overlapping ranges
        TreeMap<Integer, Integer> rangeBoundaries = new TreeMap<>();
      
        // Process each number in the input array
        for (int i = 0; i < nums.length; i++) {
            // Count frequency of current number
            frequencyMap.merge(nums[i], 1, Integer::sum);
          
            // Initialize the number itself as a potential target
            rangeBoundaries.putIfAbsent(nums[i], 0);
          
            // Mark the start of range [nums[i] - k, nums[i] + k] where elements can be changed to num
            // Increment at start of range (nums[i] - k)
            rangeBoundaries.merge(nums[i] - k, 1, Integer::sum);
          
            // Mark the end of range (exclusive) at nums[i] + k + 1
            // Decrement after end of range
            rangeBoundaries.merge(nums[i] + k + 1, -1, Integer::sum);
        }
      
        int maxResult = 0;
        int currentOverlap = 0;  // Running sum of overlapping ranges
      
        // Sweep through all boundary points in sorted order
        for (Map.Entry<Integer, Integer> entry : rangeBoundaries.entrySet()) {
            int position = entry.getKey();
            int delta = entry.getValue();
          
            // Update the current overlap count
            currentOverlap += delta;
          
            // Calculate maximum frequency achievable at this position
            // It's the minimum of:
            // 1. Total elements that can be changed to this position (currentOverlap)
            // 2. Original frequency + allowed operations
            int originalFrequency = frequencyMap.getOrDefault(position, 0);
            int achievableFrequency = Math.min(currentOverlap, originalFrequency + numOperations);
          
            // Update the maximum frequency found so far
            maxResult = Math.max(maxResult, achievableFrequency);
        }
      
        return maxResult;
    }
}