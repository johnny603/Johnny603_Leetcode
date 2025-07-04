// Attempt 1 - bruteforce

/*

class Solution {
    public char kthCharacter(long k, int[] operations) {
        StringBuilder word = new StringBuilder("a");

        int opIndex = 0;
        while (word.length() < k && opIndex < operations.length) {
            StringBuilder nextPart = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (operations[opIndex] == 0) {
                    nextPart.append(c);
                } else if (operations[opIndex] == 1) {
                    char nextChar = (c == 'z') ? 'a' : (char)(c + 1);
                    nextPart.append(nextChar);
                }
            }
            word.append(nextPart);
            opIndex++;
        }

        return word.charAt((int)k - 1); // k is 1-based
    }
}
*/

// Attempt 2 - Reverse Traversal

public class Solution {
    public char kthCharacter(long k, int[] operations) {
        int operationsCount = (int)Math.ceil(Math.log(k) / Math.log(2));
        int increases = 0;

        for (int i = operationsCount - 1; i >= 0; i--) {
            long halfSize = 1L << i;

            if (k > halfSize) {
                k -= halfSize;
                increases += operations[i]; // Safe: i is int
            }
        }

        return (char)('a' + (increases % 26));
    }
}
