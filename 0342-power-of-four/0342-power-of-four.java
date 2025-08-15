public class Solution {
    public boolean isPowerOfFour(int n) {
        // Step 1: Must be positive (no negative or zero powers)
        if (n <= 0) {
            return false;
        }

        // Step 2: Check if n is a power of 2
        //  - Power of 2 in binary has exactly one '1' bit.
        //  - The expression (n & (n - 1)) == 0 detects this pattern.
        if ((n & (n - 1)) != 0) {
            return false;
        }

        // Step 3: Ensure the single '1' bit is in the correct position for base 4.
        //  - In binary, powers of 4 look like: 1, 100, 10000, 1000000...
        //  - The positions of the '1's are odd when counting from the right starting at 1.
        //  - The mask 0x55555555 = binary 01010101010101010101010101010101
        //    This has '1's in all odd positions.
        //  - If (n & mask) != 0, it means the single '1' is in an odd position.
        if ((n & 0x55555555) == 0) {
            return false;
        }

        // Step 4: Passed all checks
        return true;
    }
}
