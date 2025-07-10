public class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] pre = new int[n + 1]; // pre[i+1] = max gap before meeting i
        int[] suf = new int[n + 1]; // suf[i] = max gap after meeting i

        int end = 0;
        int ans = startTime[0]; // Free time before the first meeting

        // Calculate prefix max gaps
        for (int i = 0; i < n; i++) {
            int gap = startTime[i] - end;
            ans = Math.max(ans, gap);
            end = endTime[i];
            pre[i + 1] = Math.max(pre[i], gap);
        }

        // Consider the gap after the last meeting
        ans = Math.max(ans, eventTime - endTime[n - 1]);

        int start = eventTime;

        // Calculate suffix max gaps
        for (int i = n - 1; i >= 0; i--) {
            int gap = start - endTime[i];
            start = startTime[i];
            suf[i] = Math.max(suf[i + 1], gap);
        }

        // Try removing one meeting and calculate max gap
        for (int i = 0; i < n; i++) {
            int prevEnd = (i == 0) ? 0 : endTime[i - 1];
            int nextStart = (i == n - 1) ? eventTime : startTime[i + 1];

            int gap = nextStart - prevEnd;
            int len = endTime[i] - startTime[i];

            if (pre[i] >= len || suf[i + 1] >= len) {
                ans = Math.max(ans, gap); // full gap available
            } else {
                ans = Math.max(ans, gap - len); // overlap with meeting length
            }
        }

        return ans;
    }
}
