class Solution {
    public char kthCharacter(int k) {
        StringBuilder word = new StringBuilder("a"); // sart with 'a'

        // Keep building the string until it has at least k characters
        while (word.length() < k) {
            StringBuilder nextPart = new StringBuilder();

            // Build next part by shifting each character
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                char nextChar = (c == 'z') ? 'a' : (char)(c + 1);
                nextPart.append(nextChar);
            }

            // Append nextPart to word
            word.append(nextPart);
        }

        // Return the k-th character (1-based index)
        return word.charAt(k - 1);
    }
}
