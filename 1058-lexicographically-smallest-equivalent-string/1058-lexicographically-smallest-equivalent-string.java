class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        DSU dsu = new DSU();

        // Build equivalence relationships
        for (int i = 0; i < s1.length(); i++) {
            dsu.union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        // Build the result string
        StringBuilder result = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            char smallestEquivalent = (char) (dsu.find(c - 'a') + 'a');
            result.append(smallestEquivalent);
        }

        return result.toString();
    }
}

// Separate class for Union-Find (DSU)
class DSU {
    int[] parent;

    public DSU() {
        parent = new int[26]; // For lowercase English letters
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;

        // Always choose the lexicographically smaller root
        if (px < py) {
            parent[py] = px;
        } else {
            parent[px] = py;
        }
    }
}
