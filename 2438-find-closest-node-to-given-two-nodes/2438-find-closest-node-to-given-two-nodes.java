/*
1. Create a helper function that returns a distance array for a given starting node
   - Use a visited array to prevent cycles
   - Record distance to each node

2. Get distances from node1 to all nodes
3. Get distances from node2 to all nodes

4. For each node, check if it's reachable from both node1 and node2
   - Compute max(distanceFromNode1[i], distanceFromNode2[i])
   - Track the node with the smallest max distance
   - If tie, choose node with smaller index

5. Return that node, or -1 if no common node
*/


class Solution {
    private int[] getDistances(int[] edges, int start) {
        int n = edges.length;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        int curr = start;
        int d = 0;

        while (curr != -1 && dist[curr] == -1) {
            dist[curr] = d;
            curr = edges[curr];
            d++;
        }

        return dist;
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int[] dist1 = getDistances(edges, node1);
        int[] dist2 = getDistances(edges, node2);

        int minDist = Integer.MAX_VALUE;
        int result = -1;

        for (int i = 0; i < edges.length; i++) {
            if (dist1[i] != -1 && dist2[i] != -1) {
                int maxDist = Math.max(dist1[i], dist2[i]);
                if (maxDist < minDist) {
                    minDist = maxDist;
                    result = i;
                } else if (maxDist == minDist && i < result) {
                    result = i;
                }
            }
        }

        return result;
    }
}
