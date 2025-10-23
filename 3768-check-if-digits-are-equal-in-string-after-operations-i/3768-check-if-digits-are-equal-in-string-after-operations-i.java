class Solution {
    public boolean hasSameDigits(String s) {
        // Convert the string into an array of integers
        int n = s.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = s.charAt(i) - '0';
        }

        // Repeat operations until only two digits remain
        while (n > 2) {
            int[] next = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                next[i] = (digits[i] + digits[i + 1]) % 10;
            }
            digits = next;
            n--;
        }

        // Return true if the final two digits are the same
        return digits[0] == digits[1];
    }
}
