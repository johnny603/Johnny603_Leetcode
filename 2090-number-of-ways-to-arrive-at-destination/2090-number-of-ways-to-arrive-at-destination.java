// we need to return an integer


class Solution {
    public int countPaths(int n, int[][] roads) {
        int MOD = 1_000_000_007; // time_i constraint

        // Step 1: Build adjacency list graph
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0], v = road[1], time = road[2];
            graph.get(u).add(new int[]{v, time});
            graph.get(v).add(new int[]{u, time});
        }

        // Step 2: Initialize Dijkstra’s algorithm
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        int[] ways = new int[n];
        ways[0] = 1;

        // Min-heap (Priority Queue) to store {distance, node}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0}); // {distance, node}

        // Step 3: Run Dijkstra's Algorithm
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long currDist = curr[0];
            int node = (int) curr[1];

            // If current distance is greater than stored shortest distance, skip it
            if (currDist > dist[node]) continue;

            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                long edgeWeight = neighbor[1];
                long newDist = currDist + edgeWeight;

                // If we found a shorter path
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    ways[nextNode] = ways[node]; // Reset paths to shortest
                    pq.offer(new long[]{newDist, nextNode});
                } else if (newDist == dist[nextNode]) {
                    // If we found another shortest path
                    ways[nextNode] = (ways[nextNode] + ways[node]) % MOD;
                }
            }
        }

        // Step 4: Return the number of ways to reach destination
        return ways[n - 1];
    }
}
