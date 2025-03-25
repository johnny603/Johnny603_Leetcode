class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;  // Pointer to the last bit of a
        int j = b.length() - 1;  // Pointer to the last bit of b
        int carry = 0;           // Carry for the next bit

        // Process both strings from right to left
        while (i >= 0 || j >= 0 || carry != 0) {
            // Get the current bit of a (0 if out of bounds)
            int bitA = (i >= 0) ? a.charAt(i--) - '0' : 0;

            // Get the current bit of b (0 if out of bounds)
            int bitB = (j >= 0) ? b.charAt(j--) - '0' : 0;

            // Add the bits and the carry
            int sum = bitA + bitB + carry;

            // Determine the new carry and the current result bit
            carry = sum / 2;
            result.append(sum % 2);
        }

        // The result is in reverse order, so reverse it before returning
        return result.reverse().toString();
    }
}
