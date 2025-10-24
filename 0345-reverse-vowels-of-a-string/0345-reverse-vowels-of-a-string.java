class Solution {
    public String reverseVowels(String s) {
        // Convert string to a char array for easy swapping
        char[] chars = s.toCharArray();
        
        // Two pointers
        int l = 0;
        int r = s.length() - 1;
        
        // Set of vowels for quick lookup (case-insensitive)
        Set<Character> vowels = new HashSet<>();
        vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u');
        vowels.add('A'); vowels.add('E'); vowels.add('I'); vowels.add('O'); vowels.add('U');

        while (l < r) {
            // Move l forward until it points to a vowel
            while (l < r && !vowels.contains(chars[l])) {
                l++;
            }
            // Move r backward until it points to a vowel
            while (l < r && !vowels.contains(chars[r])) {
                r--;
            }

            // Swap the vowels
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;

            // Move both pointers inward
            l++;
            r--;
        }

        // Convert char array back to string
        return new String(chars);
    }
}
