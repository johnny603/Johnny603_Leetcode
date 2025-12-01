class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for (int b : batteries) sum += b;

        long left = 0;
        long right = sum / n; // maximum possible time

        while (left < right) {
            long mid = right - (right - left) / 2;  // avoid infinite loop

            if (canRun(mid, batteries, n)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean canRun(long T, int[] batteries, int n) {
        long total = 0;
        for (int b : batteries) {
            total += Math.min(b, T);
        }
        return total >= T * n;
    }
}
