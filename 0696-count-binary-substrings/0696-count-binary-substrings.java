class Solution {
    public int countBinarySubstrings(String s) {
        int count = 0;
        int prevRunLength = 0; // length of previous run of 0s or 1s
        int currRunLength = 1; // length of current run (start with first char)

        // Start from the second character
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                // Continue current run
                currRunLength++;
            } else {
                // New run starts, add min(prev, curr) to count
                count += Math.min(prevRunLength, currRunLength);
                // Current run becomes previous, reset current
                prevRunLength = currRunLength;
                currRunLength = 1;
            }
        }

        // Don't forget the last run pair
        count += Math.min(prevRunLength, currRunLength);

        return count;
    }
}
