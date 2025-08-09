class Solution {
    public boolean isPowerOfTwo(int n) {
        // A power of two in binary has exactly one '1' bit (e.g., 1=0001, 2=0010, 4=0100).
        // Subtracting 1 flips all bits after the single '1' to the right of it (e.g., 4=0100, 3=0011).
        // Using (n & (n - 1)) will therefore be 0 only if n is a power of two.
        // We also check n > 0 to exclude zero and negative numbers.
        return n > 0 && (n & (n - 1)) == 0;
    }
}