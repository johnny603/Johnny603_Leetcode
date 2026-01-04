class Solution {
    public int sumFourDivisors(int[] nums) {
        int sol = 0;

        for (int n : nums) {
            sol += fourDivisorSum(n);
        }

        return sol;
    }

    private int fourDivisorSum(int n) {
        // Case 1: n = p^3
        int p = (int) Math.round(Math.cbrt(n));
        if (p * p * p == n && isPrime(p)) {
            return 1 + p + p * p + n;
        }

        // Case 2: n = p * q (p != q)
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                int j = n / i;
                if (i != j && isPrime(i) && isPrime(j)) {
                    return 1 + i + j + n;
                }
                return 0; // more than 4 divisors
            }
        }

        return 0;
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
