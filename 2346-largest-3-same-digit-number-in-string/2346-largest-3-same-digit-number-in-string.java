class Solution {
    public String largestGoodInteger(String num) {
        char maxDigit = 0; // 0 means no match found yet

        for (int i = 0; i <= num.length() - 3; i++) {
            char c = num.charAt(i);
            if (c == num.charAt(i + 1) && c == num.charAt(i + 2)) {
                if (c > maxDigit) {
                    maxDigit = c;
                }
            }
        }

        if (maxDigit == 0) {
            return "";
        } else {
            return String.valueOf(maxDigit).repeat(3);
        }
    }
}
