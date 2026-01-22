class Solution {

    // Represents an adjacent pair:
    // sum = values[i] + values[next(i)]
    // index = left index of the pair
    record Pair(long sum, int index) implements Comparable<Pair> {
        @Override
        public int compareTo(Pair other) {
            int cmp = Long.compare(this.sum, other.sum);
            return cmp != 0 ? cmp : Integer.compare(this.index, other.index);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;

        // Number of adjacent inversions (nums[i] > nums[i+1])
        int inversions = 0;

        // Sorted set of adjacent pairs (greedy: min sum, leftmost)
        TreeSet<Pair> pairSet = new TreeSet<>();

        // Count initial inversions and build initial adjacent pairs
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                inversions++;
            }
            pairSet.add(new Pair((long) nums[i] + nums[i + 1], i));
        }

        // Active indices simulate a doubly linked list
        TreeSet<Integer> activeIndices = new TreeSet<>();

        // Current values (updated after merges)
        long[] values = new long[n];

        for (int i = 0; i < n; i++) {
            activeIndices.add(i);
            values[i] = nums[i];
        }

        int operations = 0;

        // Continue until array becomes non-decreasing
        while (inversions > 0) {
            operations++;

            // Greedily pick the minimum adjacent pair
            Pair bestPair = pairSet.pollFirst();
            int leftIndex = bestPair.index;
            long mergedValue = bestPair.sum;

            int rightIndex = activeIndices.higher(leftIndex);

            // Remove inversion between left and right (if existed)
            if (values[leftIndex] > values[rightIndex]) {
                inversions--;
            }

            // Handle left neighbor
            Integer leftNeighbor = activeIndices.lower(leftIndex);
            if (leftNeighbor != null) {
                // Remove old inversion (leftNeighbor, leftIndex)
                if (values[leftNeighbor] > values[leftIndex]) {
                    inversions--;
                }

                // Remove outdated pair
                pairSet.remove(
                    new Pair(values[leftNeighbor] + values[leftIndex], leftNeighbor)
                );

                // Add new inversion if created
                if (values[leftNeighbor] > mergedValue) {
                    inversions++;
                }

                // Insert updated pair
                pairSet.add(
                    new Pair(values[leftNeighbor] + mergedValue, leftNeighbor)
                );
            }

            // Handle right neighbor
            Integer rightNeighbor = activeIndices.higher(rightIndex);
            if (rightNeighbor != null) {
                // Remove old inversion (rightIndex, rightNeighbor)
                if (values[rightIndex] > values[rightNeighbor]) {
                    inversions--;
                }

                // Remove outdated pair
                pairSet.remove(
                    new Pair(values[rightIndex] + values[rightNeighbor], rightIndex)
                );

                // Add new inversion if created
                if (mergedValue > values[rightNeighbor]) {
                    inversions++;
                }

                // Insert updated pair
                pairSet.add(
                    new Pair(mergedValue + values[rightNeighbor], leftIndex)
                );
            }

            // Apply merge
            values[leftIndex] = mergedValue;
            activeIndices.remove(rightIndex);
        }

        return operations;
    }
}
