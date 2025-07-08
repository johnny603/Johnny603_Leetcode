class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int n = events.length;

        // memo[i][k] = max value from i to end with k events left
        Integer[][] memo = new Integer[n][k + 1];

        return dfs(0, k, events, memo);
    }

    private int dfs(int i, int k, int[][] events, Integer[][] memo) {
        if (i == events.length || k == 0) return 0;
        if (memo[i][k] != null) return memo[i][k];

        // Binary search to find the next non-overlapping event
        int next = binarySearch(events, i + 1, events[i][1]);

        // Choice 1: Skip this event
        int skip = dfs(i + 1, k, events, memo);
        // Choice 2: Take this event
        int take = events[i][2] + dfs(next, k - 1, events, memo);

        return memo[i][k] = Math.max(skip, take);
    }

    // Find the next event starting AFTER endDay
    private int binarySearch(int[][] events, int startIdx, int endDay) {
        int low = startIdx, high = events.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (events[mid][0] > endDay) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
