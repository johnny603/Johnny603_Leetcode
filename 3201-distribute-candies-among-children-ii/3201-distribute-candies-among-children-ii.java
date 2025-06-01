class Solution {
    public long distributeCandies(int n, int limit) {
        long res = 0;

        // a ranges from max(0, n - 2*limit) to min(limit, n)
        int start = Math.max(0, n - 2 * limit);
        int end = Math.min(limit, n);

        for (int a = start; a <= end; a++) {
            int remaining = n - a;

            // b + c = remaining
            // b and c must be between 0 and limit
            // So we count number of integer pairs (b, c) such that:
            // max(0, remaining - limit) <= b <= min(limit, remaining)

            int min_b = Math.max(0, remaining - limit);
            int max_b = Math.min(limit, remaining);

            if (min_b <= max_b) {
                res += (max_b - min_b + 1);
            }
        }

        return res;
    }
}
