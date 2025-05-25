class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> freq = new HashMap<>(); // count word frequencies
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        int length = 0;
        boolean hasMiddle = false;

        for (String word : freq.keySet()) {
            String reversed = new StringBuilder(word).reverse().toString();
            int count = freq.get(word);

            if (!word.equals(reversed)) {
                if (freq.containsKey(reversed)) {
                    int pairs = Math.min(count, freq.get(reversed));
                    length += pairs * 4;
                    // Avoid counting same pairs again
                    freq.put(word, freq.get(word) - pairs);
                    freq.put(reversed, freq.get(reversed) - pairs);
                }
            } else {
                // It's symmetric like "gg", "cc"
                int pairs = count / 2;
                length += pairs * 4;
                // Check if we can use one symmetric word in the middle
                if (count % 2 == 1) hasMiddle = true;
            }
        }

        if (hasMiddle) length += 2;
        return length;
    }
}
