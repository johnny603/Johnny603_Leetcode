class Solution {
public:
    vector<long long> memo;
    Solution() : memo(31, -1) {}
    
    long long fib(int n) {
        if (n == 0) return 0;  // base case for fib(0)
        if (n == 1) return 1;  // base case for fib(1)
        if (memo[n] != -1) return memo[n];
        return memo[n] = fib(n - 1) + fib(n - 2);
    }
};
