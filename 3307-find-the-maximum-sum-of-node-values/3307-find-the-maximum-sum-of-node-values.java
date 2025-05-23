// any connected pair of nodes can be used. Since a tree is connected, all nodes are indirectly reachable, and there's no restriction on which edge we pick for the operation
// no need to use "edges" 2d array

class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        // stores the benefit of applying the XOR operation on each node.
        List<Integer> XOR = new ArrayList<>();
        
        // normalSum stores the sum of all original node values.
        long normalSum = 0;

        // Calculate the XOR benefit (delta) for each node and sum the original values
        for (int num : nums) {
            // (num ^ k) - num represents the gain if we apply XOR with k
            XOR.add((num ^ k) - num);
            normalSum += (long) num;
        }

        // Sort the XOR list in descending order to prioritize the most beneficial changes
        Collections.sort(XOR, Collections.reverseOrder());

        // Try to apply the XOR operation in pairs of nodes
        // Why pairs? Because each operation affects two nodes at a time
        for (int i = 0; i < XOR.size() - 1; i += 2) {
            long pairSum = XOR.get(i) + XOR.get(i + 1);

            // Only perform the operation if it increases the total sum
            if (pairSum > 0) {
                // Add the net gain from this pair to the normal sum
                normalSum += pairSum;
            }
        }

        // Return the maximum possible value of the sum after beneficial operations
        return normalSum;
    }
}
