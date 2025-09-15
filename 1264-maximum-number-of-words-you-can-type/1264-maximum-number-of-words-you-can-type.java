class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        // Split text into words
        String[] words = text.split(" ");
        int count = 0;

        // For each word, check if it can be typed
        for (String word : words) {
            boolean canType = true;
            
            // If any broken letter is in the word, mark as untypeable
            for (char c : brokenLetters.toCharArray()) {
                if (word.indexOf(c) != -1) {
                    canType = false;
                    break;
                }
            }

            // Count word if fully typeable
            if (canType) count++;
        }

        return count;
    }
}
