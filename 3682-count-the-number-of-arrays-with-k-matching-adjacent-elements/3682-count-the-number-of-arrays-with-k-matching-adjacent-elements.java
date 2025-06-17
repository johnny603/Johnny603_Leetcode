class Solution {
    static final int MOD = 1_000_000_007;
    static final int MAX = 100_005; // max n

    static long[] fact = new long[MAX];
    static long[] invFact = new long[MAX];

    public int countGoodArrays(int n, int m, int k) {
        precomputeFactorials(n);

        long res = m;
        res = res * nCr(n - 1, k) % MOD;
        res = res * modPow(m - 1, n - 1 - k) % MOD;
        return (int) res;
    }

    private void precomputeFactorials(int n) {
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[n] = modInverse(fact[n]);
        for (int i = n - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
    }

    private long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    private long modPow(long base, int exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }

    private long modInverse(long x) {
        return modPow(x, MOD - 2); // Fermat's Little Theorem
    }
}
