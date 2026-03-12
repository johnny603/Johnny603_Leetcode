class DisjointSet {
    
int[] par;
int[] depth;

public DisjointSet(int size) {

    par = new int[size];
    depth = new int[size];

    // Initially everything points to itself
    for (int i = 0; i < size; i++) {
        par[i] = i;

        // not really sure if 1 or 0 is better here, but leaving it as 1
        depth[i] = 1;
    }
}

// Typical find with path compression
public int findRoot(int node) {

    // If not root, climb up
    if (par[node] != node) {
        // compress the path while we're here
        par[node] = findRoot(par[node]);
    }

    return par[node];
}

// merge two components
// returns false if already in same component (cycle-ish case)
public boolean merge(int a, int b) {

    int rootA = findRoot(a);
    int rootB = findRoot(b);

    if (rootA == rootB) {
        return false; // already connected
    }

    // attach smaller tree under bigger one
    if (depth[rootA] < depth[rootB]) {

        // swap roots (I prefer manual swap instead of Math stuff)
        int tmp = rootA;
        rootA = rootB;
        rootB = tmp;
    }

    par[rootB] = rootA;

    // if same height, bump one up
    if (depth[rootA] == depth[rootB]) {
        depth[rootA] = depth[rootA] + 1;
    }

    return true;
}

}

class Solution {

/*
 * Quick helper to test if a spanning tree with at least
 * "minStrength" stability is possible.
 *
 * Might revisit this later if performance becomes an issue.
 */
private boolean canMakeTree(int n, int[][] edges, int k, int minStrength) {

    DisjointSet uf = new DisjointSet(n);

    int upgrades = 0;

    // keeping edges that *might* work after upgrade
    List<int[]> maybeEdges = new ArrayList<>();

    for (int i = 0; i < edges.length; i++) {

        int[] e = edges[i];

        int u = e[0];
        int v = e[1];
        int strength = e[2];
        int mustUse = e[3];

        // mandatory edges
        if (mustUse == 1) {

            // If mandatory edge fails threshold → impossible
            if (strength < minStrength) {
                return false;
            }

            uf.merge(u, v);
        }
        else {

            // already good enough
            if (strength >= minStrength) {

                uf.merge(u, v);
            }

            // could work if upgraded
            else if (strength * 2 >= minStrength) {

                // storing doubled strength though we don't really use it later
                maybeEdges.add(new int[]{strength * 2, u, v});
            }
        }
    }

    // try using upgrade edges
    for (int i = 0; i < maybeEdges.size(); i++) {

        int[] e = maybeEdges.get(i);

        int u = e[1];
        int v = e[2];

        // only connect different components
        if (uf.findRoot(u) != uf.findRoot(v)) {

            if (upgrades >= k) {
                return false;
            }

            uf.merge(u, v);
            upgrades++;
        }
    }

    // check connectivity
    int base = uf.findRoot(0);

    for (int i = 1; i < n; i++) {

        if (uf.findRoot(i) != base) {
            return false;
        }
    }

    return true;
}


public int maxStability(int n, int[][] edges, int k) {

    int left = 1;
    int right = 0;

    DisjointSet checkCycle = new DisjointSet(n);

    // figuring out upper bound for binary search
    for (int i = 0; i < edges.length; i++) {

        int[] e = edges[i];

        int u = e[0];
        int v = e[1];
        int s = e[2];

        if (e[3] == 1) {

            // mandatory edges shouldn't form cycles
            boolean ok = checkCycle.merge(u, v);

            if (!ok) {
                return -1;
            }

            right = Math.max(right, s);
        }
        else {

            // optional edges might get upgraded
            int upgraded = s * 2;

            if (upgraded > right) {
                right = upgraded;
            }
        }
    }

    int best = -1;

    // classic binary search
    while (left <= right) {

        int mid = left + (right - left) / 2; // safer version

        boolean possible = canMakeTree(n, edges, k, mid);

        if (possible) {

            best = mid;

            // try bigger
            left = mid + 1;
        }
        else {

            right = mid - 1;
        }
    }

    return best;
}

}