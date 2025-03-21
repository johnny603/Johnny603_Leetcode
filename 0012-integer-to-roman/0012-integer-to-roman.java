class Solution {
    public String intToRoman(int num) {
        // Ordered list of values and corresponding Roman numerals
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        // Iterate through the values
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result.append(numerals[i]); // Append Roman numeral
                num -= values[i]; // Subtract value
            }
        }
        
        return result.toString();
    }
}
