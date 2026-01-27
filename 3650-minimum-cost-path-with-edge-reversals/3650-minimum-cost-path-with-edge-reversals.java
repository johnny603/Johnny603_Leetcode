class Solution {
    public int minCost(int nodeCount, int[][] edges) {

        /*
         * Build adjacency list.
         * Each entry graph[u] contains {v, cost} pairs.
         *
         * Key trick:
         * - Original edge u -> v with cost w
         * - Artificial reverse edge v -> u with cost 2*w
         *
         * Example:
         *   Original: 3 -> 1 (cost 1)
         *   We add:   1 -> 3 (cost 2)
         */
        List<int[]>[] graph = new ArrayList[nodeCount];
        Arrays.setAll(graph, i -> new ArrayList<>());

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            // Normal directed edge
            graph[from].add(new int[] {to, cost});

            // Reverse edge with doubled cost
            graph[to].add(new int[] {from, cost * 2});
        }

        /*
         * Distance array:
         * minCostTo[node] = minimum cost to reach this node
         */
        final int INF = Integer.MAX_VALUE / 2;
        int[] minCostTo = new int[nodeCount];
        Arrays.fill(minCostTo, INF);

        // Start at node 0 with cost 0
        minCostTo[0] = 0;

        /*
         * Min-heap storing {currentCost, node}
         * Always expands the cheapest path first (Dijkstra)
         */
        PriorityQueue<int[]> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        minHeap.offer(new int[] {0, 0});

        // Standard Dijkstra loop
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentCost = current[0];
            int currentNode = current[1];

            // Ignore outdated entries
            if (currentCost > minCostTo[currentNode]) {
                continue;
            }

            // If we reached destination, this is the minimum cost
            if (currentNode == nodeCount - 1) {
                return currentCost;
            }

            /*
             * Explore neighbors
             *
             * Example:
             *   currentNode = 1
             *   graph[1] might contain:
             *     {0, 6}  -> reversed edge
             *     {3, 2}  -> reversed edge
             *     {2, 4}  -> normal edge
             */
            for (int[] neighbor : graph[currentNode]) {
                int nextNode = neighbor[0];
                int edgeCost = neighbor[1];

                int newCost = currentCost + edgeCost;

                // Relax edge if cheaper path found
                if (newCost < minCostTo[nextNode]) {
                    minCostTo[nextNode] = newCost;
                    minHeap.offer(new int[] {newCost, nextNode});
                }
            }
        }

        // Destination unreachable
        return -1;
    }
}
