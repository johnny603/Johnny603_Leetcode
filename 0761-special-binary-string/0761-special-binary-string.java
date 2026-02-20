class Solution {
    /**
     * Builds the lexicographically largest special binary string.
     *
     * A special binary string:
     * - Has equal number of 1s and 0s
     * - Every prefix has at least as many 1s as 0s
     */
    public String makeLargestSpecial(String s) {
        // Base case: nothing to process
        if (s.isEmpty()) {
            return "";
        }

        // Stores special substrings found at the current level
        List<String> parts = new ArrayList<>();

        // Tracks balance between '1's and '0's
        int count = 0;

        // Marks the start of the current special substring
        int segmentStart = 0;

        // Scan the string to split into primitive special substrings
        for (int i = 0; i < s.length(); i++) {
            // Update balance
            if (s.charAt(i) == '1') {
                count++;
            } else {
                count--;
            }

            // When balance becomes 0, we found a valid special substring
            if (count == 0) {
                // Recursively optimize the inner portion
                String inner = makeLargestSpecial(
                        s.substring(segmentStart + 1, i)
                );

                // Rebuild this special block
                String candidate = "1" + inner + "0";
                parts.add(candidate);

                // Move to the next segment
                segmentStart = i + 1;
            }
        }

        // Sort in descending lexicographic order for maximum result
        parts.sort(Comparator.reverseOrder());

        // Join all parts together
        return String.join("", parts);
    }
}