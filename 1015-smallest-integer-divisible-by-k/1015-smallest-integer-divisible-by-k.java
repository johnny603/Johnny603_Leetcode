class Solution {
    public int smallestRepunitDivByK(int k) {
        // A number made only of '1's cannot be divisible by any number
        // that has factor 2 or 5. (Because 10 = 2 × 5, and repunits are odd and not multiples of 5.)
        // So we can instantly reject these cases.
        if (k % 2 == 0 || k % 5 == 0)
            return -1;

        int rem = 0; // This will store the remainder of the current repunit modulo k.

        // We only need to check up to k digits.
        // Why? Because there are only k possible remainders (0 to k-1).
        // If we exceed k digits without finding remainder 0, the sequence must repeat,
        // meaning we'll never hit 0. Pigeonhole principle.
        for (int length = 1; length <= k; length++) {

            // Build the next repunit digit-by-digit using modular arithmetic:
            // Example: if rem is remainder of "111",
            // then new remainder for "1111" = (rem * 10 + 1) % k.
            rem = (rem * 10 + 1) % k;

            // If remainder becomes 0, then the repunit of `length` digits
            // ("111...1") is divisible by k.
            if (rem == 0)
                return length;
        }

        // If we exit the loop, no repunit <= k digits was divisible by k.
        return -1;
    }
}
