import java.util.Arrays;

class Solution {
    public int countDays(int days, int[][] meetings) {
        // If there are no meetings, all days are available
        if (meetings.length == 0) {
            return days;
        }

        // Step 1: Sort the meetings by their start day
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        
        // Step 2: Merge overlapping intervals
        int mergedStart = meetings[0][0];
        int mergedEnd = meetings[0][1];
        int unavailableDays = 0;

        for (int i = 1; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            // If the current meeting overlaps or touches the merged interval
            if (start <= mergedEnd) {
                mergedEnd = Math.max(mergedEnd, end); // Extend the merged interval
            } else {
                // No overlap, count the unavailable days in the current merged interval
                unavailableDays += mergedEnd - mergedStart + 1;
                mergedStart = start;
                mergedEnd = end;
            }
        }
        
        // Count the last merged interval
        unavailableDays += mergedEnd - mergedStart + 1;

        // Step 3: The total available days is the total days minus the unavailable days
        return days - unavailableDays;
    }
}
