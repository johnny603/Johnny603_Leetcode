/*
function countMaxOrSubsets(nums: int[]) -> int:
    int maxOr = 0
    for num in nums:
        maxOr |= num   // Find the maximum OR possible from all elements
    
    int count = 0

    // DFS helper function
    function dfs(index: int, currentOr: int):
        if index == nums.length:
            if currentOr == maxOr:
                count += 1
            return
        
        // Option 1: Include nums[index] in subset
        dfs(index + 1, currentOr | nums[index])

        // Option 2: Exclude nums[index] from subset
        dfs(index + 1, currentOr)

    // Start DFS from index 0 and initial OR as 0
    dfs(0, 0)

    return count
*/

class Solution {
    private int maxOr = 0;
    private int count = 0;

    public int countMaxOrSubsets(int[] nums) {
        // Step 1: Find the maximum OR value from all elements combined
        for (int num : nums) {
            maxOr |= num; // bitwise OR
        }

        // Step 2: Start DFS
        dfs(nums, 0, 0);

        return count;
    }

    private void dfs(int[] nums, int index, int currentOr) {
        // If we've considered all elements
        if (index == nums.length) {
            if (currentOr == maxOr) {
                count++;
            }
            return;
        }

        // Include current element in the subset
        dfs(nums, index + 1, currentOr | nums[index]);

        // Exclude current element from the subset
        dfs(nums, index + 1, currentOr);
    }
}
