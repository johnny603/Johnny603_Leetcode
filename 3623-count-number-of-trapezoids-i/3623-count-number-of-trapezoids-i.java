class Solution {
    static final long MOD = 1_000_000_007;

    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> freq = new HashMap<>();
        
        for (int[] p : points) {
            freq.merge(p[1], 1, Integer::sum);
        }

        long S = 0;    // sum(fi)
        long SQ = 0;   // sum(fi^2)

        for (int count : freq.values()) {
            if (count < 2) continue;
            long f = (long)count * (count - 1) / 2 % MOD;
            S = (S + f) % MOD;
            SQ = (SQ + f * f % MOD) % MOD;
        }

        long ans = (S*S % MOD - SQ + MOD) % MOD;
        long inv2 = (MOD + 1) / 2;   // modular inverse of 2 in mod 1e9+7
        ans = ans * inv2 % MOD;

        return (int) ans;
    }
}
