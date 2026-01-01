class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // Traverse from the last digit to the first
        for (int i = n - 1; i >= 0; i--) {

            // If the current digit is less than 9,
            // we can safely add 1 and return the result
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            // If the digit is 9, it becomes 0 due to carry
            digits[i] = 0;
        }

        // If we reach here, all digits were 9
        // Example: [9,9,9] -> [1,0,0,0]
        int[] result = new int[n + 1];
        result[0] = 1; // The leading 1, rest are 0 by default

        return result;
    }
}
