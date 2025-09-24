class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        // Handle sign
        if ((numerator < 0) ^ (denominator < 0)) {
            result.append("-");
        }

        // Convert to long to avoid overflow (e.g., Integer.MIN_VALUE / -1)
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Integer part
        result.append(num / den);
        long remainder = num % den;

        if (remainder == 0) {
            return result.toString(); // no fractional part
        }

        result.append(".");
        Map<Long, Integer> remainderMap = new HashMap<>();

        // Fractional part
        while (remainder != 0) {
            if (remainderMap.containsKey(remainder)) {
                // Insert parentheses at the index where the repeating remainder first appeared
                int startIndex = remainderMap.get(remainder);
                result.insert(startIndex, "(");
                result.append(")");
                break;
            }

            // Remember the position of this remainder
            remainderMap.put(remainder, result.length());

            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }

        return result.toString();
    }
}
