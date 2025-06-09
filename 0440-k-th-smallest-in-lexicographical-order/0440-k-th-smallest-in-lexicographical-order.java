class Solution {
    public int findKthNumber(int n, int k) {
        int current = 1;
        k--;  // we start from 1, so we do one step manually

        while (k > 0) {
            long count = countSteps(n, current, current + 1);
            if (count <= k) {
                // Move to next prefix
                current++;
                k -= count;
            } else {
                // Go deeper in the tree (add a zero)
                current *= 10;
                k--;
            }
        }

        return current;
    }

    // Counts the number of integers between [prefix, nextPrefix)
    private long countSteps(int n, long prefix, long nextPrefix) {
        long count = 0;
        while (prefix <= n) {
            count += Math.min(n + 1L, nextPrefix) - prefix;
            prefix *= 10;
            nextPrefix *= 10;
        }
        return count;
    }
}
