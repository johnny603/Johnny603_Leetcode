class Solution {
    private static final long MOD = 1_000_000_007;

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // Step 1: Add boundaries
        int[] h = new int[hFences.length + 2];
        int[] v = new int[vFences.length + 2];

        h[0] = 1;
        h[h.length - 1] = m;
        v[0] = 1;
        v[v.length - 1] = n;

        System.arraycopy(hFences, 0, h, 1, hFences.length);
        System.arraycopy(vFences, 0, v, 1, vFences.length);

        // Step 2: Sort
        Arrays.sort(h);
        Arrays.sort(v);

        // Step 3: All horizontal distances
        Set<Long> horizontalDistances = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                horizontalDistances.add((long) h[j] - h[i]);
            }
        }

        // Step 4: Match with vertical distances
        long maxSide = -1;
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                long dist = (long) v[j] - v[i];
                if (horizontalDistances.contains(dist)) {
                    maxSide = Math.max(maxSide, dist);
                }
            }
        }

        if (maxSide == -1) return -1;

        // Step 5: Return area
        return (int) ((maxSide * maxSide) % MOD);
    }
}
