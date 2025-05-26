class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            indegree[v]++;
        }

        int[][] colorCount = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;

            int colorIdx = colors.charAt(node) - 'a';
            colorCount[node][colorIdx]++;
            if (colorCount[node][colorIdx] > max) {
                max = colorCount[node][colorIdx];
            }

            for (int i = 0; i < graph.get(node).size(); i++) {
                int neighbor = graph.get(node).get(i);
                for (int c = 0; c < 26; c++) {
                    if (colorCount[node][c] > colorCount[neighbor][c]) {
                        colorCount[neighbor][c] = colorCount[node][c];
                    }
                }
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (visited == n) {
            return max;
        } else {
            return -1;
        }
    }
}
