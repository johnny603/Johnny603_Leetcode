class Solution {
  public int maxFreeTime(int eventDuration, int maxMoves, int[] meetingStarts, int[] meetingEnds) {
    // Compute gaps between meetings and at the start/end of the event
    int[] freeGaps = computeGaps(eventDuration, meetingStarts, meetingEnds);

    // Calculate the sum of the first (maxMoves + 1) gaps as initial window
    int currentWindowSum = 0;
    for (int i = 0; i <= maxMoves && i < freeGaps.length; i++) {
      currentWindowSum += freeGaps[i];
    }

    int maxFreeTime = currentWindowSum;

    // Use sliding window to find the maximum sum of (maxMoves + 1) consecutive gaps
    for (int i = maxMoves + 1; i < freeGaps.length; i++) {
      currentWindowSum += freeGaps[i] - freeGaps[i - maxMoves - 1];
      maxFreeTime = Math.max(maxFreeTime, currentWindowSum);
    }

    return maxFreeTime;
  }

  /**
   * Computes the gaps (free time) before, between, and after meetings.
   * Example:
   *   If eventDuration = 10 and meetings = [0,1],[2,3],[4,6]
   *   Then:
   *     gap before first = 0 (start at 0)
   *     gap between 1st and 2nd = 2 - 1 = 1
   *     gap between 2nd and 3rd = 4 - 3 = 1
   *     gap after last = 10 - 6 = 4
   */
  private int[] computeGaps(int eventDuration, int[] meetingStarts, int[] meetingEnds) {
    int n = meetingStarts.length;
    int[] gaps = new int[n + 1];

    // Free time before the first meeting
    gaps[0] = meetingStarts[0];

    // Free time between consecutive meetings
    for (int i = 1; i < n; i++) {
      gaps[i] = meetingStarts[i] - meetingEnds[i - 1];
    }

    // Free time after the last meeting
    gaps[n] = eventDuration - meetingEnds[n - 1];

    return gaps;
  }
}
