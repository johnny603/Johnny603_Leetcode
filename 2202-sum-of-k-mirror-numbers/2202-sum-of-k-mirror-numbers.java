class Solution {

    // Main method to find the sum of the first `count` k-mirror numbers in a given base
    public long kMirror(int base, int count) {
        long sum = 0;

        // Try increasing palindrome lengths until we've found enough valid numbers
        for (int length = 1; ; length++) {
            // Start and end define the prefix space to generate palindromes
            int start = (int) Math.pow(10, (length - 1) / 2);
            int end = (int) Math.pow(10, (length + 1) / 2);

            // Iterate through all valid prefixes to generate base-10 palindromes
            for (int i = start; i < end; i++) {
                long palindrome = i;

                int j;
                if (length % 2 == 0) {
                    j = i; // Even-length palindrome, mirror entire prefix
                } else {
                    j = i / 10; // Odd-length palindrome, drop last digit
                }

                // Build the full palindrome by appending reversed digits
                while (j > 0) {
                    int digit = j % 10;
                    palindrome = palindrome * 10 + digit;
                    j /= 10;
                }

                // Convert to base `k`
                String baseKString = Long.toString(palindrome, base);

                // Check if base-k representation is also a palindrome
                if (isPalindrome(baseKString.toCharArray())) {
                    sum += palindrome;
                    count--;
                    if (count == 0) return sum;
                }
            }
        }
    }

    // Helper method to check if a character array form a palindrome
    private boolean isPalindrome(char[] charArray) {
        int left = 0, right = charArray.length - 1;
        while (left < right) {
            if (charArray[left] != charArray[right]) return false;
            left++;
            right--;
        }
        return true;
    }
}
