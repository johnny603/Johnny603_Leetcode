class Solution {
    private List<List<Integer>> graph;
    private int[] nums;
    private int[] subtreeXor;
    private int[] parent;
    private int totalXor;
    
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        
        // Build adjacency list
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        // Calculate total XOR
        totalXor = 0;
        for (int num : nums) {
            totalXor ^= num;
        }
        
        // Root the tree at node 0 and compute subtree XORs
        subtreeXor = new int[n];
        parent = new int[n];
        Arrays.fill(parent, -1);
        dfs(0, -1);
        
        int minScore = Integer.MAX_VALUE;
        
        // Try all pairs of edges
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                minScore = Math.min(minScore, calculateScore(edges[i], edges[j]));
            }
        }
        
        return minScore;
    }
    
    private void dfs(int node, int par) {
        parent[node] = par;
        subtreeXor[node] = nums[node];
        
        for (int child : graph.get(node)) {
            if (child != par) {
                dfs(child, node);
                subtreeXor[node] ^= subtreeXor[child];
            }
        }
    }
    
    private int calculateScore(int[] edge1, int[] edge2) {
        int minScore = Integer.MAX_VALUE;
        
        // Try both orientations for each edge
        int[][] orientations1 = {{edge1[0], edge1[1]}, {edge1[1], edge1[0]}};
        int[][] orientations2 = {{edge2[0], edge2[1]}, {edge2[1], edge2[0]}};
        
        for (int[] e1 : orientations1) {
            for (int[] e2 : orientations2) {
                if (isValidRemoval(e1[0], e1[1], e2[0], e2[1])) {
                    minScore = Math.min(minScore, computeScore(e1[0], e1[1], e2[0], e2[1]));
                }
            }
        }
        
        return minScore;
    }
    
    private boolean isValidRemoval(int p1, int c1, int p2, int c2) {
        // Check if p1 is parent of c1 and p2 is parent of c2
        return parent[c1] == p1 && parent[c2] == p2;
    }
    
    private int computeScore(int p1, int c1, int p2, int c2) {
        int xor1 = subtreeXor[c1];
        int xor2 = subtreeXor[c2];
        int xor3;
        
        // Check if one subtree contains the other
        if (isAncestor(c1, c2)) {
            // c2 is descendant of c1
            xor1 ^= xor2; // Remove c2's subtree from c1's subtree
            xor3 = totalXor ^ subtreeXor[c1]; // Everything not in c1's original subtree
        } else if (isAncestor(c2, c1)) {
            // c1 is descendant of c2
            xor2 ^= xor1; // Remove c1's subtree from c2's subtree
            xor3 = totalXor ^ subtreeXor[c2]; // Everything not in c2's original subtree
        } else {
            // Disjoint subtrees
            xor3 = totalXor ^ xor1 ^ xor2;
        }
        
        int max = Math.max(xor1, Math.max(xor2, xor3));
        int min = Math.min(xor1, Math.min(xor2, xor3));
        return max - min;
    }
    
    private boolean isAncestor(int ancestor, int descendant) {
        int current = descendant;
        while (current != -1) {
            if (current == ancestor) {
                return true;
            }
            current = parent[current];
        }
        return false;
    }
}