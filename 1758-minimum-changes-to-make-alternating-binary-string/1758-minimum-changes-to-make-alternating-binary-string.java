class Solution {
    public int minOperations(String s) {
        int count1 = 0; // pattern starting with '0' -> "010101"
        int count2 = 0; // pattern starting with '1' -> "101010"

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // pattern "0101..."
            if ((i % 2 == 0 && c != '0') || (i % 2 == 1 && c != '1')) {
                count1++;
            }

            // pattern "1010..."
            if ((i % 2 == 0 && c != '1') || (i % 2 == 1 && c != '0')) {
                count2++;
            }
        }

        return Math.min(count1, count2);
    }
}