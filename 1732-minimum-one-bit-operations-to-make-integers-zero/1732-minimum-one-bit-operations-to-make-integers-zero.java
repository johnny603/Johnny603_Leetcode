class Solution {
    public int minimumOneBitOperations(int n) {
        int ans = 0;
        while (n > 0) {
            
            // XOR the current value of ans with n.
            // This reverses the Gray code transformation.
            // Each XOR step accumulates the number of bit flips
            // required to reach 0 from the current n.
            ans ^= n;

            // Right shift n by 1 bit.
            // This moves the next bit into position for processing
            // (essentially dividing n by 2 and discarding the least significant bit).
            n >>= 1;
        }
        return ans;
    }
}
