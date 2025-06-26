class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;

        int low = 1;
        int high = x / 2; // Optimization: sqrt(x) <= x / 2 for x >= 4

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long square = (long) mid * mid; // Avoid overflow

            if (square == x) {
                return mid;
            } else if (square < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high; // high will be the integer part of sqrt(x)
    }
}
