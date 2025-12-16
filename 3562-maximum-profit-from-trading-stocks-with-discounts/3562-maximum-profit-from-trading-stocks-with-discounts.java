class Solution {

    static final int MAXN = 160;
    static final int NEG = -1_000_000_000;

    // dp[u][state] = DP array for employee u
    // state: 0 = buy full price, 1 = not buy, 2 = buy discounted
    int[][][] dp = new int[MAXN][3][];
    List<Integer>[] adj = new ArrayList[MAXN];

    // Knapsack merge helper
    void knapsack(int[] dpArr, int[] A, int[] B, int from, int to) {
        for (int i = from; i >= to; i--) {
            if (dpArr[i] <= NEG) continue;
            for (int j = from - i; j >= 0; j--) {
                int bestChild = Math.max(A[j], B[j]);
                if (bestChild <= NEG) continue;
                dpArr[i + j] = Math.max(dpArr[i + j], dpArr[i] + bestChild);
            }
        }
    }

    // DFS to compute DP arrays
    void dfs(int u, int budget, int[] present, int[] future) {

        // First process all children
        for (int v : adj[u]) dfs(v, budget, present, future);

        int originalCost = present[u];
        int discountedCost = originalCost / 2;

        dp[u][0] = new int[budget + 1];
        dp[u][1] = new int[budget + 1];
        dp[u][2] = new int[budget + 1];

        Arrays.fill(dp[u][0], NEG);
        Arrays.fill(dp[u][1], NEG);
        Arrays.fill(dp[u][2], NEG);

        dp[u][0][0] = dp[u][1][0] = dp[u][2][0] = 0;

        if (originalCost <= budget) dp[u][0][originalCost] = future[u] - originalCost;
        if (discountedCost <= budget) dp[u][2][discountedCost] = future[u] - discountedCost;

        for (int v : adj[u]) {
            // u not bought → children cannot get discount
            knapsack(dp[u][1], dp[v][0], dp[v][1], budget, 0);
            // u bought full → children may get discount
            knapsack(dp[u][0], dp[v][1], dp[v][2], budget, originalCost);
            // u bought discounted → children may get discount
            knapsack(dp[u][2], dp[v][1], dp[v][2], budget, discountedCost);
        }
    }

    // Main function
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {

        // Initialize adjacency list
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        // Build tree
        for (int[] h : hierarchy) {
            int boss = h[0] - 1;
            int emp = h[1] - 1;
            adj[boss].add(emp);
        }

        // Run DFS from CEO (employee 0)
        dfs(0, budget, present, future);

        // Return the maximum profit of buying or not buying root
        int bestBuy = Arrays.stream(dp[0][0]).max().getAsInt();
        int bestNotBuy = Arrays.stream(dp[0][1]).max().getAsInt();
        return Math.max(bestBuy, bestNotBuy);
    }
}
