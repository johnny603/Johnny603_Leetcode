class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;

        // traverse from right to left (ignore MSB at index 0)
        for (int i = s.length() - 1; i > 0; i--) {
            int bit = s.charAt(i) - '0';

            // effective bit after carry
            if (bit + carry == 1) {
                // odd → add 1 then divide by 2
                steps += 2;
                carry = 1;
            } else {
                // even → just divide by 2
                steps += 1;
            }
        }

        // if carry remains, we need one extra step
        return steps + carry;
    }
}