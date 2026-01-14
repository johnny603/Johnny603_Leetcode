import java.util.*;

class SegmentTree {
    private final int n;                 // number of segments = xs.length - 1
    private final int[] xs;
    private final int[] coveredCount;
    private final int[] coveredWidth;

    public SegmentTree(int[] xs) {
        this.xs = xs;
        this.n = xs.length - 1;
        this.coveredCount = new int[4 * n];
        this.coveredWidth = new int[4 * n];
    }

    // Adds val to the interval [i, j)
    public void add(int i, int j, int val) {
        add(0, 0, n - 1, i, j, val);
    }

    // Returns total covered width
    public int getCoveredWidth() {
        return coveredWidth[0];
    }

    private void add(int idx, int lo, int hi, int i, int j, int val) {
        if (j <= xs[lo] || xs[hi + 1] <= i) return;

        if (i <= xs[lo] && xs[hi + 1] <= j) {
            coveredCount[idx] += val;
        } else {
            int mid = (lo + hi) >>> 1;
            add(idx * 2 + 1, lo, mid, i, j, val);
            add(idx * 2 + 2, mid + 1, hi, i, j, val);
        }

        if (coveredCount[idx] > 0) {
            coveredWidth[idx] = xs[hi + 1] - xs[lo];
        } else if (lo == hi) {
            coveredWidth[idx] = 0;
        } else {
            coveredWidth[idx] =
                coveredWidth[idx * 2 + 1] + coveredWidth[idx * 2 + 2];
        }
    }
}

class Solution {
    public double separateSquares(int[][] squares) {
        List<int[]> events = new ArrayList<>(); // {y, delta, xl, xr}
        TreeSet<Integer> xsSet = new TreeSet<>();

        for (int[] sq : squares) {
            int x = sq[0], y = sq[1], l = sq[2];
            events.add(new int[]{y, 1, x, x + l});
            events.add(new int[]{y + l, -1, x, x + l});
            xsSet.add(x);
            xsSet.add(x + l);
        }

        events.sort(Comparator.comparingInt(a -> a[0]));

        int[] xs = new int[xsSet.size()];
        int idx = 0;
        for (int v : xsSet) xs[idx++] = v;

        double halfArea = getTotalArea(events, xs) / 2.0;

        SegmentTree tree = new SegmentTree(xs);
        long area = 0;
        int prevY = events.get(0)[0];

        for (int[] e : events) {
            int y = e[0], delta = e[1], xl = e[2], xr = e[3];

            long width = tree.getCoveredWidth();
            long areaGain = width * (long)(y - prevY);

            if (area + areaGain >= halfArea) {
                return prevY + (halfArea - area) / width;
            }

            area += areaGain;
            tree.add(xl, xr, delta);
            prevY = y;
        }

        throw new IllegalStateException();
    }

    private long getTotalArea(List<int[]> events, int[] xs) {
        SegmentTree tree = new SegmentTree(xs);
        long area = 0;
        int prevY = events.get(0)[0];

        for (int[] e : events) {
            int y = e[0], delta = e[1], xl = e[2], xr = e[3];
            area += (long) tree.getCoveredWidth() * (y - prevY);
            tree.add(xl, xr, delta);
            prevY = y;
        }

        return area;
    }
}
