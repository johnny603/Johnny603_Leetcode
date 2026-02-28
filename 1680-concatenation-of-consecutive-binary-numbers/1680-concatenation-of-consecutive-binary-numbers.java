class Solution {
    public int concatenatedBinary(int n) {
        long sol = 0;
        int MOD = 1_000_000_007;
        int bitLength = 0;

        // get the bit representation of each integer
        for (int i = 1; i <= n; i++) {

            // if i is a power of 2, increase bit length
            if ((i & (i - 1)) == 0) {
                bitLength++;
            }

            // shift left to make room for new binary digits
            sol = ((sol << bitLength) + i) % MOD;
        }

        return (int) sol;
    }
}