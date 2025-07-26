class Solution {
  public long maxSubarrays(int n, int[][] conflictingPairs) {
    long totalValidSubarrays = 0;

    // Track the farthest left conflict that blocks subarrays ending at current index
    int mostRestrictiveLeft = 0;
    int secondMostRestrictiveLeft = 0;

    // gains[i]: extra valid subarrays we get if we remove a conflict that ends at index i
    long[] removableGains = new long[n + 1];

    // conflictMap[end]: list of left indices that conflict with subarrays ending at `end`
    List<Integer>[] conflictMap = new List[n + 1];

    for (int i = 0; i <= n; ++i)
      conflictMap[i] = new ArrayList<>();

    // Populate the conflict map
    for (int[] pair : conflictingPairs) {
      int left = Math.min(pair[0], pair[1]);
      int right = Math.max(pair[0], pair[1]);
      conflictMap[right].add(left);
    }

    // Iterate over all possible right-endpoints of subarrays
    for (int end = 1; end <= n; ++end) {
      // For each conflicting left endpoint that blocks ending at `end`
      for (int left : conflictMap[end]) {
        if (left > mostRestrictiveLeft) {
          secondMostRestrictiveLeft = mostRestrictiveLeft;
          mostRestrictiveLeft = left;
        } else if (left > secondMostRestrictiveLeft) {
          secondMostRestrictiveLeft = left;
        }
      }

      // Count subarrays [mostRestrictiveLeft + 1, end] to [end, end] as valid
      totalValidSubarrays += end - mostRestrictiveLeft;

      // If we remove the most restrictive conflict, we can gain:
      // [secondMostRestrictiveLeft + 1, end] to [mostRestrictiveLeft, end]
      // => (mostRestrictiveLeft - secondMostRestrictiveLeft) subarrays
      removableGains[mostRestrictiveLeft] += mostRestrictiveLeft - secondMostRestrictiveLeft;
    }

    // Return total valid subarrays + best possible gain from removing one conflict
    return totalValidSubarrays + Arrays.stream(removableGains).max().getAsLong();
  }
}
