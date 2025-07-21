class Solution {
    public String makeFancyString(String s) {
        StringBuilder result = new StringBuilder();
        int count = 0; // Count of consecutive characters

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            // If the current character is same as the previous one, increment the count
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                count = 1; // reset count for new character
            }

            // Only add character if it doesn't exceed 2 consecutive occurrences
            if (count <= 2) {
                result.append(current);
            }
        }

        return result.toString();
    }
}
