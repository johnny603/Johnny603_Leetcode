class Solution {
public:
    // dp[index][phase][started] stores the maximum sum we can get starting at
    // `index`, being in the `phase` of the sequence, and whether we have
    // started picking elements
    long long dp[100001][3][2];

    vector<int> nums;     // input array
    int n;                // size of input array
    long long INF = 1e16; // sentinel value for uninitialized dp

    // Recursive function to calculate max sum
    // i: current index in nums
    // phase: 0 -> first increasing segment
    //        1 -> decreasing segment
    //        2 -> second increasing segment
    // started: 0 -> haven't picked any element yet
    //          1 -> started picking elements
    long long dfs(int i, int phase, int started) {
        // If we've completed all 3 phases, no more elements to add
        if (phase == 3)
            return 0;

        // If we reached the end of the array without completing all phases
        if (i == n)
            return -INF;

        // Return precomputed value if already calculated
        if (dp[i][phase][started] != INF)
            return dp[i][phase][started];

        long long maxSum = -INF;

        if (!started) {
            // Option 1: skip current element and continue
            maxSum = max(maxSum, dfs(i + 1, phase, 0));
            // Option 2: start picking this element
            maxSum = max(maxSum, nums[i] + dfs(i + 1, phase, 1));
        } else {
            // We have already started, so we need to respect the phase rules
            if (phase == 0 && nums[i] > nums[i - 1]) {
                // Continue increasing segment or move to next phase
                maxSum = max(maxSum, nums[i] + dfs(i + 1, phase, 1));
                maxSum = max(maxSum, nums[i] + dfs(i + 1, phase + 1, 1));
            }
            if (phase == 1 && nums[i] < nums[i - 1]) {
                // Continue decreasing segment or move to next phase
                maxSum = max(maxSum, nums[i] + dfs(i + 1, phase, 1));
                maxSum = max(maxSum, nums[i] + dfs(i + 1, phase + 1, 1));
            }
            if (phase == 2 && nums[i] > nums[i - 1]) {
                // Continue second increasing segment or finish
                maxSum = max(maxSum, nums[i] + dfs(i + 1, phase, 1));
                maxSum = max(maxSum, nums[i] + dfs(i + 1, phase + 1, 1));
            }
        }

        // Memoize and return the maximum sum
        return dp[i][phase][started] = maxSum;
    }

    long long maxSumTrionic(vector<int>& input) {
        nums = input;
        n = nums.size();

        // Initialize dp with sentinel value (INF) to indicate unvisited states
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        // Start recursion from index 0, phase 0, not started
        return dfs(0, 0, 0);
    }
};
