class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k;              // total binary codes
        HashSet<Integer> seen = new HashSet<>();

        int mask = need - 1;            // keeps last k bits
        int value = 0;

        for (int i = 0; i < s.length(); i++) {
            // shift left and add new bit
            value = ((value << 1) & mask) | (s.charAt(i) - '0');

            // start recording once window size reaches k
            if (i >= k - 1) {
                seen.add(value);

                if (seen.size() == need) {
                    return true;        // early success
                }
            }
        }

        return false;
    }
}