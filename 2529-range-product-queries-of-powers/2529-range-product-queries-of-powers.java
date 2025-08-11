class Solution {
    static final int MOD = 1_000_000_007;

    public int[] productQueries(int n, int[][] queries) {
        // Build powers array
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            if ((n & (1 << i)) != 0) { // << is a bitwise left shift operation
                list.add(1 << i);
            }
        }
        // Convert to array
        int[] powers = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            powers[i] = list.get(i);
        }

        // Prefix product
        long[] prefix = new long[powers.length + 1];
        prefix[0] = 1;
        for (int i = 0; i < powers.length; i++) {
            prefix[i + 1] = (prefix[i] * powers[i]) % MOD;
        }

        // Answer queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            long numerator = prefix[r + 1];
            long denominator = prefix[l];
            long inv = modPow(denominator, MOD - 2, MOD); // modular inverse
            ans[i] = (int)((numerator * inv) % MOD);
        }
        return ans;
    }

    // Fast exponentiation
    private long modPow(long base, long exp, int mod) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
