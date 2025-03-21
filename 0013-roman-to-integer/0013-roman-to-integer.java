class Solution {
    public static int romanToInt(String s) {
        // Define a HashMap for Roman numerals and their corresponding integer values
        HashMap<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        
        int total = 0;
        
        // Traverse through the string
        for (int i = 0; i < s.length(); i++) {
            // Get the current and next Roman numeral values
            int currentVal = romanMap.get(s.charAt(i));
            int nextVal = (i + 1 < s.length()) ? romanMap.get(s.charAt(i + 1)) : 0;

            // If the next value is greater, subtract the current value (e.g., IV = 4)
            if (currentVal < nextVal) {
                total -= currentVal;
            } else {
                total += currentVal;
            }
        }
        
        return total;
    }

}
