/*
class Solution {
    public int countGoodNumbers(long n) {
        int good;
        if (n.length == 0) { // base case
             return 0; 
        }

        for (int k = 0; k < n.length; k++) {
            if (k % 2 == 0) { // even digits
                if (n[k] % 2 == 0) {
                    good++;
                }   
            }
            if (k % 2 != 0) { // odd digits
                if (n[k] is prime) {
                    good++;
                }
            }
        }
        return good % 10^9 + 7;
    }
}
*/

// Idea:
// int good
// Use recursion
// even is k % 2 == 0 and odd is k % 2 != 0
// int evenDigit;
// int oddDigit;
// if evenDigit % 2 == 0
// if oddDigit / oddDigit == 0
// if the last two if statements pass, then goodDigit++
// return goodDigit % 10^9 + 7


// Input
// n = 1 (one digit long)
// 0 is prime
// 2 is even
// 6 is even
// 8 is even

class Solution {
    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long evenCount = (n + 1) / 2; // even indices
        long oddCount = n / 2;        // odd indices

        long evenWays = power(5, evenCount);
        long oddWays = power(4, oddCount);

        return (int)((evenWays * oddWays) % MOD);
    }

    private long power(long base, long exp) {
        long result = 1;
        base = base % MOD;

        while (exp > 0) {
            if ((exp % 2) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp = exp / 2;
        }

        return result;
    }
}
