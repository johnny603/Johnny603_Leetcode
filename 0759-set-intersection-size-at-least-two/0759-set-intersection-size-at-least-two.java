import java.util.*;

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (x, y) ->
            x[1] == y[1] ? y[0] - x[0] : x[1] - y[1]
        );

        int p1 = -1, p2 = -1; // p1 < p2 are the last two picked numbers
        int ans = 0;

        for (int[] in : intervals) {
            int l = in[0], r = in[1];

            if (p1 >= l && p2 >= l) {
                // already have two points inside this interval
                continue;
            } 
            else if (p2 >= l) {
                // exactly one point (p2) inside; add r
                p1 = p2;
                p2 = r;
                ans += 1;
            } 
            else {
                // no points inside; add r-1 and r
                p1 = r - 1;
                p2 = r;
                ans += 2;
            }
        }

        return ans;
    }
}
