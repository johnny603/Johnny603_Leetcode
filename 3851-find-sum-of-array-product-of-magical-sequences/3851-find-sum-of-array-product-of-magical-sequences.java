public class Solution {
    static final long MOD = 1_000_000_007L;
    static Combination comb = new Combination(55);

    public int magicalSum(int M, int K, int[] A) {
        int n = A.length;
        long[][][][] dp = new long[2][55][M + 1][K + 1];

        // Precompute A[i]^p for all i, p
        long[][] power = new long[n][M + 1];
        for (int i = 0; i < n; i++) {
            power[i][0] = 1;
            for (int j = 1; j <= M; j++)
                power[i][j] = (power[i][j - 1] * A[i]) % MOD;
        }

        dp[0][0][0][0] = 1; // base state
        long res = 0;

        for (int i = 0; i < n; i++) {
            int cur = i & 1, next = (i + 1) & 1;

            // Reset dp[next]
            for (long[][] arr : dp[next])
                for (long[] row : arr)
                    Arrays.fill(row, 0);

            for (int carry = 0; carry <= 50; carry++) {
                for (int used = 0; used <= M; used++) {
                    for (int set = 0; set <= K; set++) {
                        long val = dp[cur][carry][used][set];
                        if (val == 0) continue;

                        for (int now = 0; now + used <= M; now++) {
                            long curVal = power[i][now] * comb.nCr(M - used, now) % MOD;
                            long newVal = val * curVal % MOD;

                            int totalUsed = used + now;
                            int totalSet = set + ((carry + now) & 1);
                            int newCarry = (carry + now) >> 1;

                            if (totalSet > K) break;

                            if (totalUsed == M) {
                                int bits = totalSet + Long.bitCount(newCarry);
                                if (bits == K)
                                    res = (res + newVal) % MOD;
                            } else if (i + 1 < n) {
                                dp[next][newCarry][totalUsed][totalSet] =
                                    (dp[next][newCarry][totalUsed][totalSet] + newVal) % MOD;
                            }
                        }
                    }
                }
            }
        }

        return (int) res;
    }

    /** Fast modular exponentiation */
    static long modPow(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return res;
    }

    /** Precompute factorials and modular inverses */
    static class Combination {
        long[] fact, inv;
        long mod;

        Combination(int size) {
            this.mod = MOD;
            fact = new long[size + 1];
            inv = new long[size + 1];
            fact[0] = 1;
            for (int i = 1; i <= size; i++)
                fact[i] = fact[i - 1] * i % mod;
            inv[size] = modPow(fact[size], mod - 2, mod);
            for (int i = size; i >= 1; i--)
                inv[i - 1] = inv[i] * i % mod;
        }

        long nCr(int n, int r) {
            if (n < r || n < 0 || r < 0) return 0;
            return fact[n] * inv[r] % mod * inv[n - r] % mod;
        }
    }
}
