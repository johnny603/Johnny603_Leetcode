class Solution {
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1; // complement of 0 is 1
        }

        String original = Integer.toBinaryString(n);
        StringBuilder complement = new StringBuilder();

        // complement the original binary string
        for (char c : original.toCharArray()) {
            if (c == '0') {
                complement.append('1');
            } else {
                complement.append('0');
            }
        }

        // convert complemented binary string back to integer
        return Integer.parseInt(complement.toString(), 2);
    }
}