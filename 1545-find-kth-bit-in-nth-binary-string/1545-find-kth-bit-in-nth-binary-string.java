class Solution {
    public char findKthBit(int n, int k) {
        // Base case
        if (n == 1) return '0';

        int len = (1 << n) - 1;      // 2^n - 1
        int mid = (len / 2) + 1;     // middle position

        // Middle case
        if (k == mid) return '1';

        // Left side case
        if (k < mid) {
            return findKthBit(n - 1, k);
        }

        // Right side case
        char bit = findKthBit(n - 1, len - k + 1);

        // invert
        return bit == '0' ? '1' : '0';
    }
}