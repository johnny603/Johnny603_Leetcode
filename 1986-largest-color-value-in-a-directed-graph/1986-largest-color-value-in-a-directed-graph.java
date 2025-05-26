class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }

        // colorCount[i][c] = max count of color c (0-25) at node i
        int[][] colorCount = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        
        int visited = 0;
        int max = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;

            int colorIdx = colors.charAt(node) - 'a';
            colorCount[node][colorIdx]++;
            max = Math.max(max, colorCount[node][colorIdx]);
            
            for (int neighbor : graph.get(node)) {
                for (int c = 0; c < 26; c++) {
                    colorCount[neighbor][c] = Math.max(colorCount[neighbor][c], colorCount[node][c]);
                }
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) queue.offer(neighbor);
            }
        }
        
        return visited == n ? max : -1; // If not all nodes were visited, there's a cycle
    }
}
