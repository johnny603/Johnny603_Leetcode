class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        // Compute total area and bounds
        for (int[] s : squares) {
            double y = s[1], l = s[2];
            totalArea += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double target = totalArea / 2.0;

        // Binary search
        for (int i = 0; i < 60; i++) {
            double mid = (low + high) / 2.0;
            double areaBelow = areaBelow(squares, mid);

            if (areaBelow < target) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private double areaBelow(int[][] squares, double y) {
        double area = 0;
        for (int[] s : squares) {
            double y0 = s[1], l = s[2];
            if (y <= y0) continue;
            if (y >= y0 + l) {
                area += l * l;
            } else {
                area += (y - y0) * l;
            }
        }
        return area;
    }
}
