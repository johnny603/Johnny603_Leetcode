class Solution {
    public int maximum69Number (int num) {
        char[] digits = String.valueOf(num).toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') { // First 6 found
                digits[i] = '9';    // Change it to 9
                break;              // Stop after one change
            }
        }
        return Integer.parseInt(new String(digits));
    }
}
