class Solution {
    public int maxFreqSum(String s) {
        // Vowels set for quick lookup
        String vowels = "aeiou";

        // Track max frequencies
        int maxVowel = 0;
        int maxConsonant = 0;

        // Count frequency of each character
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Find max among vowels and consonants
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();

            if (vowels.indexOf(c) != -1) { // vowel
                maxVowel = Math.max(maxVowel, count);
            } else { // consonant
                maxConsonant = Math.max(maxConsonant, count);
            }
        }

        return maxVowel + maxConsonant;
    }
}
