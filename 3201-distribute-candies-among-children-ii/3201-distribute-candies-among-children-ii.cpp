class Solution {
public:
    long long distributeCandies(int n, int limit) {
        long long res = 0;

        int start = std::max(0, n - 2 * limit);
        int end = std::min(limit, n);

        for (int a = start; a <= end; ++a) {
            int remaining = n - a;

            int min_b = std::max(0, remaining - limit);
            int max_b = std::min(limit, remaining);

            if (min_b <= max_b) {
                res += (long long)(max_b - min_b + 1);
            }
        }

        return res;
    }
};
