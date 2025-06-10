class Solution {
    public int maxDifference(String s) {
        HashMap<Character, Integer> freq = new HashMap<>();

        // Count frequencies
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int maxOdd = Integer.MIN_VALUE;
        int minEven = Integer.MAX_VALUE;

        // Separate odd and even frequencies
        for (int count : freq.values()) {
            if (count % 2 == 1) {
                maxOdd = Math.max(maxOdd, count);
            } else {
                minEven = Math.min(minEven, count);
            }
        }

        // Return result if both exist
        if (maxOdd == Integer.MIN_VALUE || minEven == Integer.MAX_VALUE) {
            return -1; // No valid pair
        }

        return maxOdd - minEven;
    }
}
