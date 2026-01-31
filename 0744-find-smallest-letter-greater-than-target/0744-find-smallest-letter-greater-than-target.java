class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        // for each character in letters compare it with target
        // Return the smallest character in letters that is lexicographically greater than target
        // If such a character does not exist, return the first character in letters.
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > target) {
                return letters[i];
            }
        }
        return letters[0];
    }
}