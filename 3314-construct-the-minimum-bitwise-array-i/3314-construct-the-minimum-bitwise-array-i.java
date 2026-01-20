class Solution {

    public int[] minBitwiseArray(List<Integer> nums) {
        // Result array to store the minimum valid value for each nums[i]
        int[] answer = new int[nums.size()];

        // Iterate through each number in the input list
        for (int index = 0; index < nums.size(); index++) {
            int targetValue = nums.get(index);
            int smallestValid = -1; // Default if no valid value exists

            // Try all possible candidates less than targetValue
            // We start from 1 because ans[i] must be non-negative
            for (int candidate = 1; candidate < targetValue; candidate++) {

                // Check if candidate OR (candidate + 1) equals the target
                if ((candidate | (candidate + 1)) == targetValue) {
                    smallestValid = candidate;
                    break; // First valid candidate is the minimum
                }
            }

            // Store the result for this index
            answer[index] = smallestValid;
        }

        return answer;
    }
}
