class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();

        // evenOddSets[0] = all reachable zero-count states with even parity
        // evenOddSets[1] = all reachable zero-count states with odd parity
        TreeSet<Integer>[] evenOddSets = new TreeSet[2];
        Arrays.setAll(evenOddSets, i -> new TreeSet<>());

        // Populate all possible counts of zeros (0..n) grouped by parity
        for (int zeros = 0; zeros <= n; zeros++) {
            evenOddSets[zeros % 2].add(zeros);
        }

        // Count initial number of zeros in the string
        int initialZeroCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                initialZeroCount++;
            }
        }

        // Remove starting state so we don't revisit it
        evenOddSets[initialZeroCount % 2].remove(initialZeroCount);

        // BFS queue over "number of zeros remaining"
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(initialZeroCount);

        int operations = 0;

        // Standard BFS over state space
        while (!queue.isEmpty()) {

            // Process one BFS level (one operation)
            for (int levelSize = queue.size(); levelSize > 0; --levelSize) {
                int currentZeros = queue.poll();

                // If no zeros remain, string is all '1's
                if (currentZeros == 0) {
                    return operations;
                }

                /*
                 When flipping exactly k bits:
                 - Suppose we flip x zeros to ones
                 - Then we must flip (k - x) ones to zeros

                 New zero count =
                     currentZeros - x + (k - x)
                   = currentZeros + k - 2x

                 x is bounded by how many zeros/ones exist.
                 These bounds produce the [minZeros, maxZeros] range.
                */

                int minZeros = currentZeros + k - 2 * Math.min(currentZeros, k);
                int maxZeros = currentZeros + k - 2 * Math.max(k - n + currentZeros, 0);

                // Only states with matching parity are reachable
                TreeSet<Integer> candidateSet = evenOddSets[minZeros % 2];

                // Get first reachable state in range
                Integer nextZeros = candidateSet.ceiling(minZeros);

                // Add all reachable states within [minZeros, maxZeros]
                while (nextZeros != null && nextZeros <= maxZeros) {
                    queue.offer(nextZeros);
                    candidateSet.remove(nextZeros); // mark visited
                    nextZeros = candidateSet.ceiling(minZeros);
                }
            }

            operations++;
        }

        // If BFS finishes without reaching 0 zeros
        return -1;
    }
}