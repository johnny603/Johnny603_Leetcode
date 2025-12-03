class Solution {
    public int longestPalindrome(String s) {
        int[] freq = new int[128];  // supports uppercase + lowercase
        
        for (char c : s.toCharArray()) {
            freq[c]++;
        }

        int length = 0;
        boolean hasOdd = false;

        for (int count : freq) {
            length += (count / 2) * 2;  // use the even part
            if (count % 2 == 1) {
                hasOdd = true;         // keep track of at least one odd
            }
        }

        // If there's any odd count, we can put one in the middle
        if (hasOdd) length++;

        return length;
    }
}
