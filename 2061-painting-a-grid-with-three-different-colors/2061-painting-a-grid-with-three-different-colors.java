class Solution {
    private final int MOD = 1_000_000_007;
    List<int[]> validCols = new ArrayList<>();
    Map<Integer, List<Integer>> transitions = new HashMap<>();

    public int colorTheGrid(int m, int n) {
        // Generate valid states for a single column
        generateStates(new int[m], 0);

        int stateCount = validCols.size();

        // Build transition map
        for (int i = 0; i < stateCount; i++) {
            transitions.put(i, new ArrayList<>());
            for (int j = 0; j < stateCount; j++) {
                if (isCompatible(validCols.get(i), validCols.get(j))) {
                    transitions.get(i).add(j);
                }
            }
        }

        // DP: dp[col][state]
        int[] dp = new int[stateCount];
        Arrays.fill(dp, 1); // First column can be any valid state

        for (int col = 1; col < n; col++) {
            int[] newDp = new int[stateCount];
            for (int curr = 0; curr < stateCount; curr++) {
                for (int prev : transitions.get(curr)) {
                    newDp[curr] = (newDp[curr] + dp[prev]) % MOD;
                }
            }
            dp = newDp;
        }

        int result = 0;
        for (int val : dp) {
            result = (result + val) % MOD;
        }

        return result;
    }

    private void generateStates(int[] curr, int pos) {
        if (pos == curr.length) {
            validCols.add(curr.clone());
            return;
        }
        for (int c = 0; c < 3; c++) {
            if (pos > 0 && curr[pos - 1] == c) continue;
            curr[pos] = c;
            generateStates(curr, pos + 1);
        }
    }

    // check if adjacent are the same color
    private boolean isCompatible(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) return false;
        }
        return true;
    }
}
