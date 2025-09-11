class Solution {
    public String sortVowels(String s) {
        // Define vowels
        String vowelsSet = "aeiouAEIOU";
        
        // Step 1: collect vowels
        StringBuilder vowels = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (vowelsSet.indexOf(ch) != -1) {
                vowels.append(ch);
            }
        }
        
        // Step 2: sort vowels by ASCII
        char[] vowelArr = vowels.toString().toCharArray();
        java.util.Arrays.sort(vowelArr);
        
        // Step 3: rebuild string
        StringBuilder result = new StringBuilder();
        int i = 0; // index for sorted vowels
        for (char ch : s.toCharArray()) {
            if (vowelsSet.indexOf(ch) != -1) {
                result.append(vowelArr[i++]);
            } else {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
}
