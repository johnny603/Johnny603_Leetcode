class Solution {
    static final int MOD = (int)1e9 + 7;
    static final int N = (int)1e4 + 5;

    int[][] dp = new int[21][N];
    int[][] lengths = new int[N][21];

    // lengths[j][k] --> How many unique paths are there that end at 'j' & have length of 'k'
    void calculateUniquePathLengths() {
        for (int[] row : lengths) {
            Arrays.fill(row, 0);
        }

        for (int j = 1; j < N; j++) {
            lengths[j][1] = 1;
            for (int k = j + j; k < N; k += j) {
                for (int i = 0; i < 20; i++) {
                    lengths[k][i + 1] += lengths[j][i];
                }
            }
        }
    }

    // How many unique arrangements of length 'length' & having all the values from [1 .. mx]
    int uniqueArrangements(int length, int mx) {
        if (length == 0) return (mx == 0) ? 1 : 0;
        if (mx == 0) return 0;

        if (dp[mx][length] != -1) return dp[mx][length];

        int result = (uniqueArrangements(length - 1, mx) + uniqueArrangements(length - 1, mx - 1)) % MOD;
        dp[mx][length] = result;
        return result;
    }

    public int idealArrays(int n, int maxValue) {
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        calculateUniquePathLengths();

        long ans = 0;
        for (int last = maxValue; last >= 1; last--) {
            for (int j = 1; j < 21; j++) {
                long arrangements = uniqueArrangements(n, j);
                ans = (ans + arrangements * lengths[last][j]) % MOD;
            }
        }

        return (int) ans;
    }
}
