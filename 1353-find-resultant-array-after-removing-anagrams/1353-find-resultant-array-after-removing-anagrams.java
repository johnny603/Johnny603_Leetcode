class Solution {
    public List<String> removeAnagrams(String[] words) {
        // Initialize result list
        List<String> result = new ArrayList<>();
        
        // Add the first word by default
        result.add(words[0]);
        
        // Iterate from the second word to the end
        for (int i = 1; i < words.length; i++) {
            // Check if current word is NOT an anagram of the previous word in result
            if (!isAnagram(result.get(result.size() - 1), words[i])) {
                // If not an anagram, add it to the result
                result.add(words[i]);
            }
        }
        
        return result;
    }
    
    // Helper function to check if two words are anagrams
    private boolean isAnagram(String a, String b) {
        // If lengths are different, they cannot be anagrams
        if (a.length() != b.length()) return false;
        
        // Count character frequencies for both strings
        int[] count = new int[26];
        for (int i = 0; i < a.length(); i++) {
            count[a.charAt(i) - 'a']++;
            count[b.charAt(i) - 'a']--;
        }
        
        // If all counts are zero, they are anagrams
        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }
}
