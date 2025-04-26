class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long result = 0;
        int start = -1, minPos = -1, maxPos = -1;
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            // If the current number is out of bounds, reset the window
            if (num < minK || num > maxK) {
                start = i;
                minPos = -1;
                maxPos = -1;
            }
            
            if (num == minK) minPos = i;
            if (num == maxK) maxPos = i;
            
            if (minPos != -1 && maxPos != -1) {
                result += Math.max(0, Math.min(minPos, maxPos) - start);
            }
        }
        
        return result;
    }
}
