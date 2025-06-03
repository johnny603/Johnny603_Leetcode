import java.util.*;

class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] hasKey = new boolean[n];
        boolean[] visited = new boolean[n];
        boolean[] inQueue = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> boxSet = new HashSet<>();

        for (int box : initialBoxes) {
            boxSet.add(box);
            if (status[box] == 1) {
                queue.offer(box);
                inQueue[box] = true;
            }
        }

        int totalCandies = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (visited[curr]) continue;

            visited[curr] = true;
            totalCandies += candies[curr];

            // Gain keys
            for (int key : keys[curr]) {
                if (!hasKey[key]) {
                    hasKey[key] = true;
                    if (boxSet.contains(key) && !inQueue[key]) {
                        queue.offer(key);
                        inQueue[key] = true;
                    }
                }
            }

            // Gain new boxes
            for (int box : containedBoxes[curr]) {
                boxSet.add(box);
                if ((status[box] == 1 || hasKey[box]) && !inQueue[box]) {
                    queue.offer(box);
                    inQueue[box] = true;
                }
            }
        }

        return totalCandies;
    }
}
