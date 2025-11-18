class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;

        while (i < n - 1) {        // stop at second-to-last
            if (bits[i] == 1) {
                i += 2;            // two-bit character
            } else {
                i += 1;            // one-bit character
            }
        }

        // If we stop on the last index, last char is 1-bit
        return i == n - 1;
    }
}
