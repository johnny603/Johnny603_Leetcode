class Solution {
    public int possibleStringCount(String word, int k) {
        final int MOD = 1000000007;
        
        // Step 1: Group consecutive characters
        List<Integer> groups = new ArrayList<>();
        int count = 1;
        
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                groups.add(count);
                count = 1;
            }
        }
        groups.add(count);
        
        int n = groups.size();
        
        // Calculate total possible combinations
        long totalCombinations = 1;
        for (int groupSize : groups) {
            totalCombinations = (totalCombinations * groupSize) % MOD;
        }
        
        // If minimum possible length >= k, all combinations are valid
        if (k <= n) {
            return (int) totalCombinations;
        }
        
        // Step 2: DP with prefix sum optimization
        // dp[j] = number of ways to get exactly j characters
        long[] dp = new long[k];
        dp[0] = 1;
        
        for (int i = 0; i < n; i++) {
            int groupSize = groups.get(i);
            long[] newDp = new long[k];
            
            // Calculate prefix sums for optimization
            long[] prefixSum = new long[k + 1];
            for (int j = 0; j < k; j++) {
                prefixSum[j + 1] = (prefixSum[j] + dp[j]) % MOD;
            }
            
            for (int j = 0; j < k; j++) {
                // Current group can contribute 1 to groupSize characters
                // So we sum dp[j-1] + dp[j-2] + ... + dp[j-groupSize]
                int minPrev = Math.max(0, j - groupSize);
                int maxPrev = j - 1;
                
                if (maxPrev >= 0) {
                    long sum = (prefixSum[maxPrev + 1] - prefixSum[minPrev] + MOD) % MOD;
                    newDp[j] = sum;
                }
            }
            
            dp = newDp;
        }
        
        // Step 3: Calculate invalid combinations (length < k)
        long invalidCombinations = 0;
        for (int j = 0; j < k; j++) {
            invalidCombinations = (invalidCombinations + dp[j]) % MOD;
        }
        
        long result = (totalCombinations - invalidCombinations + MOD) % MOD;
        return (int) result;
    }
}