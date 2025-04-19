import java.util.HashSet;

class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getSumOfSquares(n);
        }

        return n == 1;
    }

    private int getSumOfSquares(int number) {
        int sum = 0;
        while (number > 0) {
            int digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }
        return sum;
    }
}

// Idea:
// Hashset to track digits that we used
// For the positive integer
// Split number into digits using the idea of mod and dividing by 10
// square each digit
// find the sum of the digits
// if reduced to one return true
// else break out of the loop and return false