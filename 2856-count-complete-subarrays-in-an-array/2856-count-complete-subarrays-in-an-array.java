import java.util.*;

class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        int result = 0;

        // Step 1: Count the number of unique elements in the whole array
        Set<Integer> uniqueElements = new HashSet<>();
        for (int num : nums) {
            uniqueElements.add(num);
        }
        int totalUnique = uniqueElements.size();

        // Step 2: Use nested loops to find all subarrays
        for (int start = 0; start < n; start++) {
            Set<Integer> currentSet = new HashSet<>();
            for (int end = start; end < n; end++) {
                currentSet.add(nums[end]); // add current element
                if (currentSet.size() == totalUnique) {
                    result++; // this subarray has all unique elements
                }
            }
        }

        return result;
    }
}
