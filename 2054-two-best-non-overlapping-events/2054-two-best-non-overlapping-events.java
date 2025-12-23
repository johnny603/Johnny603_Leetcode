class Solution {
    public int maxTwoEvents(int[][] events) {
        // Sort events by end time
        Arrays.sort(events, (a, b) -> Integer.compare(a[1], b[1]));

        int n = events.length;

        // dp[i] = max value among events[0..i]
        int[] dp = new int[n];
        dp[0] = events[0][2];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], events[i][2]);
        }

        int ans = dp[n - 1]; // case of choosing only one event

        for (int i = 0; i < n; i++) {
            int start = events[i][0];
            int value = events[i][2];

            // binary search for last event ending < start
            int left = 0, right = i - 1;
            int idx = -1;

            while (left <= right) {
                int mid = (left + right) / 2;
                if (events[mid][1] < start) {
                    idx = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (idx != -1) {
                ans = Math.max(ans, value + dp[idx]);
            } else {
                ans = Math.max(ans, value);
            }
        }

        return ans;
    }
}
