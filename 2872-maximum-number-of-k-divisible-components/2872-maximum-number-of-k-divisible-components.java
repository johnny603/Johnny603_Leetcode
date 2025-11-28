class Solution {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int[] count = new int[1];

        dfs(0, graph, values, visited, k, count);
        return count[0];
    }

    private long dfs(int node, List<List<Integer>> graph, int[] values, boolean[] vis, int k, int[] count) {
        vis[node] = true;
        long sum = values[node];

        for (int nbr : graph.get(node)) {
            if (!vis[nbr]) {
                sum += dfs(nbr, graph, values, vis, k, count);
            }
        }

        if (sum % k == 0) {
            count[0]++;
            return 0;
        }

        return sum;
    }
}
