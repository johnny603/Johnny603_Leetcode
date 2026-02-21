class Solution {
    public int countPrimeSetBits(int left, int right) {
        int count = 0;

        for (int num = left; num <= right; num++) {
            int bits = Integer.bitCount(num); // counts 1s in binary

            if (isPrime(bits)) {
                count++;
            }
        }

        return count;
    }

    // helper to check prime
    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}