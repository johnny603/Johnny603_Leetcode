class Solution {
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;         // make space for the next bit
            result |= (n & 1);    // add the last bit of n
            n >>= 1;              // remove last bit from n
        }
        return result;
    }
}