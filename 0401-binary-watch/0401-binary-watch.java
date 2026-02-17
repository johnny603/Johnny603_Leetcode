class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        
        // start backtracking:
        // idx = current LED index (0..9)
        // on = how many LEDs are currently turned on
        // hour/minute = current built time
        dfs(0, turnedOn, 0, 0, 0, result);
        
        return result;
    }

    private void dfs(int idx, int target, int on, int hour, int minute, List<String> result) {
        // prune invalid states
        if (hour >= 12 || minute >= 60 || on > target) return;

        // if we considered all 10 LEDs
        if (idx == 10) {
            if (on == target) {
                // format minute with leading zero if needed
                String time = hour + ":" + (minute < 10 ? "0" + minute : minute);
                result.add(time);
            }
            return;
        }

        // ----- choose LED ON -----
        if (idx < 4) {
            // first 4 LEDs belong to hour
            dfs(idx + 1, target, on + 1, hour + (1 << idx), minute, result);
        } else {
            // last 6 LEDs belong to minute
            dfs(idx + 1, target, on + 1, hour, minute + (1 << (idx - 4)), result);
        }

        // ----- choose LED OFF -----
        dfs(idx + 1, target, on, hour, minute, result);
    }
}
