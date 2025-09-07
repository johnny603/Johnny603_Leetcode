class Solution {
    public int[] sumZero(int n) {
        int[] result = new int[n];
        int index = 0;

        // Fill with pairs: (1, -1), (2, -2), ...
        for (int i = 1; i <= n / 2; i++) {
            result[index++] = i;
            result[index++] = -i;
        }

        // If n is odd, the last spot stays 0
        // which keeps the sum balanced.
        return result;
    }
}
