class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];     // dp[i] = length of largest subset ending at i
        int[] prev = new int[n];   // prev[i] = index of previous element in subset
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }

        // Reconstruct the subset
        List<Integer> result = new ArrayList<>();
        while (maxIndex >= 0) {
            result.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];
        }

        Collections.reverse(result);
        return result;
    }
}


// nums = [1, 2, 3]
// 1 % 2 = 1
// 1 % 3 = 1
// 2 % 1 = 0
// ...
// array answer is created by numbers that equal 0 when %
// Need two for loops
// Idea: Find all possible comvinations to use %, use || to explain the contraints of the problem, then append the pair to new array