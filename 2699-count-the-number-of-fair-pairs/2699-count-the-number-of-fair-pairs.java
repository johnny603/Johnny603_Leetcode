import java.util.Arrays;

class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums); // sort in scending order to perform a binary search
        long fairPairs = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int left = lowerBound(nums, i + 1, nums.length - 1, lower - nums[i]);
            int right = upperBound(nums, i + 1, nums.length - 1, upper - nums[i]);
            
            fairPairs += (right - left + 1);
        }
        
        return fairPairs;
    }

    // Find the first index where nums[i] >= target
    private int lowerBound(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        return start;
    }

    // Find the last index where nums[i] <= target
    private int upperBound(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) start = mid + 1;
            else end = mid - 1;
        }
        return end;
    }
}
