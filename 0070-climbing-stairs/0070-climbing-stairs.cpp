class Solution {
public:
    vector<long long> memo; // Declare the memoization table

    Solution() : memo(100, -1) {} // Initialize memo inside constructor

    long long climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        if (memo[n] != -1) return memo[n]; // Use direct index lookup

        return memo[n] = climbStairs(n - 1) + climbStairs(n - 2);
    }
};


// n = 0, ways = 0
// n = 1, ways = 1
// n = 2, ways = 2
// n = 3, ways = 3
// n = 4, ways = 5 = 3 + 2
// n = 5, ways = 8 = 5 + 3
// n = 6, ways = 13 = 8 + 5
// Fibonnacci sequence

// use memoization for large values by storing already computed values
