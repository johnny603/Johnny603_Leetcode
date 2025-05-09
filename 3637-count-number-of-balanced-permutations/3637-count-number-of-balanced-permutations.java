public class Solution {
    int mod = (int)1e9 + 7;
    int[] freq = new int[10]; // Frequency of each digit 0-9
    int[] fact;               // Factorials modulo mod
    int[] ifact;              // Inverse factorials modulo mod
    int n;                    // Length of the input string
    int target;               // Half of the digit sum
    int[][][] cache;          // DP cache

    // Binary exponentiation: computes base^exp % mod
    long binaryExpo(int base, int exp) {
        long result = 1;
        long b = base;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * b) % mod;
            }
            b = (b * b) % mod;
            exp /= 2;
        }
        return result;
    }

    public int countBalancedPermutations(String num) {
        n = num.length();
        Arrays.fill(freq, 0);
        fact = new int[n + 1];
        ifact = new int[n + 1];
        int sum = 0;

        // Count digit frequencies and compute sum of digits
        for (char ch : num.toCharArray()) {
            int digit = ch - '0';
            sum += digit;
            freq[digit]++;
        }

        // If sum is odd, it's impossible to split evenly
        if (sum % 2 != 0) return 0;
        target = sum / 2;

        // Precompute factorials and inverse factorials
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (int)((1L * i * fact[i - 1]) % mod);
        }
        for (int i = 0; i <= n; i++) {
            ifact[i] = (int)binaryExpo(fact[i], mod - 2);
        }

        // Initialize cache: 10 digits, half length, and sum up to target
        cache = new int[10][n / 2 + 1][target + 1];
        for (int[][] matrix : cache) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }

        // Start recursive DP from digit 0, oddSize 0, currentSum 0
        return solve(0, 0, 0);
    }

    // Recursive DP function to distribute digits into two equal-sized sets with equal sum
    int solve(int digit, int oddSize, int currSum) {
        // Base case: all digits considered
        if (digit == 10) {
            // If both sets are half the size and sums are equal, count permutations
            if (oddSize == n / 2 && currSum == target) {
                int evenSize = n - oddSize;
                return (int)((1L * fact[oddSize] * fact[evenSize]) % mod);
            } else {
                return 0;
            }
        }

        // Prune invalid states
        if (currSum > target) return 0;

        // Return cached result if available
        if (cache[digit][oddSize][currSum] != -1) {
            return cache[digit][oddSize][currSum];
        }

        int ans = 0;

        // Try placing 0 to freq[digit] instances in the odd set
        for (int oddCount = 0; oddCount <= Math.min(freq[digit], n / 2 - oddSize); oddCount++) {
            int evenCount = freq[digit] - oddCount;

            // Calculate permutations using multinomial coefficient logic
            long ways = ((1L * ifact[oddCount] * ifact[evenCount]) % mod);
            ways = (ways * solve(digit + 1, oddSize + oddCount, currSum + oddCount * digit)) % mod;

            ans = (int)((ans + ways) % mod);
        }

        // Store and return result
        return cache[digit][oddSize][currSum] = ans;
    }
}
