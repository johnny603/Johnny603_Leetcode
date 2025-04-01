class Solution {
public:
    long long mostPoints(vector<vector<int>>& questions) {
        int n = questions.size();
        vector<long long> dp(n + 1, 0);  // dp[i] stores the max points from question i to the end
        
        // Iterate over the questions in reverse order
        for (int i = n - 1; i >= 0; i--) {
            long long points = questions[i][0];  // Cast points to long long
            int brainpower = questions[i][1];
            
            // Either skip this question, or solve it and skip brainpower[i] questions ahead
            if (i + brainpower + 1 < n) {
                dp[i] = max(dp[i + 1], points + dp[i + brainpower + 1]);
            } else {
                dp[i] = max(dp[i + 1], points);
            }
        }
        
        return dp[0];  // The maximum points starting from the first question
    }
};
