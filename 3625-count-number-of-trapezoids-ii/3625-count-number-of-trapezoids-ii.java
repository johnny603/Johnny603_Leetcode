class Solution {

    public int countTrapezoids(int[][] points) {

        // Store lines grouped by slope → (intercept → count)
        Map<Slope, Map<Integer, Integer>> slopeGroups = new HashMap<>();

        // Store lines grouped by direction vector → (intercept → count)
        // Used to eliminate parallel but collinear duplicates (avoid overcounting)
        Map<Slope, Map<Integer, Integer>> vectorGroups = new HashMap<>();

        int n = points.length;

        // Iterate through all point pairs (i, j) to define line segments
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                // Normalize direction so dx is always non-negative
                // or if dx == 0, dy must be non-negative
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }

                // Reduce (dx, dy) using gcd so slope is stored in simplest form
                int g = gcd(dx, dy);
                int slopeX = dx / g;
                int slopeY = dy / g;

                // Compute "intercept-like" value:
                // For slope vector (sx, sy), the perpendicular offset is:
                //   d = sx * y - sy * x
                int intercept = slopeX * points[i][1] - slopeY * points[i][0];

                Slope slopeKey = new Slope(slopeX, slopeY);  // normalized slope
                Slope vectorKey = new Slope(dx, dy);         // raw vector (for duplicate elimination)

                // Count lines by slope & intercept
                slopeGroups
                        .computeIfAbsent(slopeKey, k -> new HashMap<>())
                        .merge(intercept, 1, Integer::sum);

                // Count identical vectors for correction term
                vectorGroups
                        .computeIfAbsent(vectorKey, k -> new HashMap<>())
                        .merge(intercept, 1, Integer::sum);
            }
        }

        // Count trapezoids:
        // Parallel lines give potential trapezoids → countPairs(slopeGroups)
        // But we subtract overcounted collinear duplicates → countPairs(vectorGroups) / 2
        return countPairs(slopeGroups) - countPairs(vectorGroups) / 2;
    }

    // Euclidean gcd
    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    /**
     * Counts number of unordered pairs in each group.
     * For counts {c1, c2, ..., ck}, the number of pairs = sum over i<j of (ci * cj).
     */
    private int countPairs(Map<Slope, Map<Integer, Integer>> groupedLines) {
        int totalPairs = 0;

        for (Map<Integer, Integer> interceptCounts : groupedLines.values()) {

            int remainingTotal = interceptCounts.values().stream().mapToInt(Integer::intValue).sum();

            for (int count : interceptCounts.values()) {
                remainingTotal -= count;
                totalPairs += (long) count * remainingTotal;
            }
        }
        return totalPairs;
    }

    /**
     * Slope class used as a map key. Stores pair (x, y) representing slope = y/x.
     */
    private static class Slope {
        private final int x;
        private final int y;

        public Slope(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Slope)) return false;

            Slope other = (Slope) obj;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
