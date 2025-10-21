class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        // frequency of each number in the original array
        Map<Integer, Integer> frequencyMap = new HashMap<>();
      
        // track intervals where we can perform operations
        // difference array technique to efficiently track overlapping ranges
        TreeMap<Integer, Integer> differenceArray = new TreeMap<>();
      
        // Process each number in the input array
        for (int i = 0; i < nums.length; i++) {
            // Count frequency of current number
            frequencyMap.merge(nums[i], 1, Integer::sum);
          
            // Mark the position of the original number (with 0 increment initially)
            differenceArray.putIfAbsent(nums[i], 0);
          
            // Mark the start of the range [num - k, num + k] where we can change values to num
            // Increment at start of range
            differenceArray.merge(nums[i] - k, 1, Integer::sum);
          
            // Mark the end of the range (exclusive) where we stop being able to change values to num
            // Decrement after end of range
            differenceArray.merge(nums[i] + k + 1, -1, Integer::sum);
        }
      
        int maxResult = 0;
        int currentOverlappingRanges = 0; // Number of overlapping ranges at current position
      
        // Traverse through all positions in sorted order
        for (Map.Entry<Integer, Integer> entry : differenceArray.entrySet()) {
            int position = entry.getKey();
            int deltaValue = entry.getValue();
          
            // Update the count of overlapping ranges at this position
            currentOverlappingRanges += deltaValue;
          
            // Calculate maximum frequency achievable at this position:
            // - Original frequency of this value (if it exists in nums)
            // - Plus up to numOperations changes from other values within range
            // - Limited by the number of values that can be changed to this position
            int achievableFrequency = Math.min(
                currentOverlappingRanges, 
                frequencyMap.getOrDefault(position, 0) + numOperations
            );
          
            // Update the maximum frequency found so far
            maxResult = Math.max(maxResult, achievableFrequency);
        }
      
        return maxResult;
    }
}