class Solution {
    private int mod = (int)1e9 + 7;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[][] base = new int[26][26];
        for (int i = 0; i < 26; i++) {
            int steps = nums.get(i);
            for (int j = 1; j <= steps; j++) {
                int next = (i + j) % 26;
                base[i][next] = (base[i][next] + 1) % mod; // Replace s[i] with the next nums[s[i] - 'a'] consecutive characters in the alphabet
            }
        }

        int[][] T = matrixPower(base, t);

        // Multiply freq vector with transition matrix
        int result = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                result = (int)((result + 1L * freq[i] * T[i][j]) % mod);
            }
        }

        return result;
    }

    private int[][] matrixPower(int[][] matrix, long power) {
        int size = matrix.length;
        int[][] result = new int[size][size];
        // Initialize result as identity matrix
        for (int i = 0; i < size; i++) result[i][i] = 1;

        while (power > 0) {
            if ((power & 1) == 1) result = multiply(result, matrix);
            matrix = multiply(matrix, matrix);
            power >>= 1;
        }

        return result;
    }

    private int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    C[i][j] = (int)((C[i][j] + 1L * A[i][k] * B[k][j]) % mod);
                }
            }
        }

        return C;
    }
}
