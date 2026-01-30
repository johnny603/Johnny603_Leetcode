class TrieNode {
    // Children for each lowercase letter
    TrieNode[] next = new TrieNode[26];

    // Unique ID if this node represents the end of a transformation string
    int transformId = -1;
}

class Solution {

    // Large value to represent unreachable states
    private static final long INF = 1L << 60;

    // Trie root for all transformation strings
    private TrieNode root = new TrieNode();

    // Counter for assigning unique IDs to transformation strings
    private int transformCount = 0;

    // costGraph[i][j] = min cost to convert transform i → transform j
    private long[][] costGraph;

    // Source and target strings as char arrays for fast access
    private char[] src;
    private char[] tgt;

    // dp[i] = min cost to convert src[i..end] → tgt[i..end]
    private Long[] dp;

    public long minimumCost(
        String source, String target,
        String[] original, String[] changed, int[] cost) {

        int m = cost.length;

        src = source.toCharArray();
        tgt = target.toCharArray();

        // Worst case: each original and changed string is unique
        costGraph = new long[m * 2][m * 2];

        // Initialize graph with INF (except diagonal)
        for (int i = 0; i < costGraph.length; i++) {
            Arrays.fill(costGraph[i], INF);
            costGraph[i][i] = 0;
        }

        // Insert all transformation strings into the trie
        // and record direct transformation costs
        for (int i = 0; i < m; i++) {
            int fromId = insert(original[i]);
            int toId = insert(changed[i]);
            costGraph[fromId][toId] = Math.min(costGraph[fromId][toId], cost[i]);
        }

        // Floyd–Warshall:
        // Allows chaining transformations on the SAME substring interval
        for (int k = 0; k < transformCount; k++) {
            for (int i = 0; i < transformCount; i++) {
                if (costGraph[i][k] >= INF) continue;
                for (int j = 0; j < transformCount; j++) {
                    costGraph[i][j] = Math.min(
                        costGraph[i][j],
                        costGraph[i][k] + costGraph[k][j]
                    );
                }
            }
        }

        // Interval DP with memoization
        dp = new Long[src.length];
        long ans = dfs(0);

        return ans >= INF ? -1 : ans;
    }

    /**
     * Inserts a transformation string into the trie
     * Assigns a unique ID if it ends a new word
     */
    private int insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.next[idx] == null) {
                node.next[idx] = new TrieNode();
            }
            node = node.next[idx];
        }
        if (node.transformId == -1) {
            node.transformId = transformCount++;
        }
        return node.transformId;
    }

    /**
     * DFS + memoization:
     * Finds minimum cost to transform src[pos..] → tgt[pos..]
     */
    private long dfs(int pos) {
        // Reached the end: no cost needed
        if (pos == src.length) return 0;

        // Already solved
        if (dp[pos] != null) return dp[pos];

        long best = INF;

        // Option 1: characters already match, skip
        if (src[pos] == tgt[pos]) {
            best = dfs(pos + 1);
        }

        // Option 2: try every valid substring starting at pos
        TrieNode srcNode = root;
        TrieNode tgtNode = root;

        for (int end = pos; end < src.length; end++) {

            srcNode = srcNode.next[src[end] - 'a'];
            tgtNode = tgtNode.next[tgt[end] - 'a'];

            // Stop if either substring doesn't exist
            if (srcNode == null || tgtNode == null) break;

            // Only consider full transformation strings
            if (srcNode.transformId == -1 || tgtNode.transformId == -1) continue;

            long transformCost =
                costGraph[srcNode.transformId][tgtNode.transformId];

            if (transformCost < INF) {
                best = Math.min(best, transformCost + dfs(end + 1));
            }
        }

        dp[pos] = best;
        return best;
    }
}
