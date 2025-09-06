class Solution {
  public long minOperations(int[][] queries) {
    long totalOperations = 0;

    // Process each query range [left, right]
    for (int[] query : queries) {
      final int left = query[0];
      final int right = query[1];

      // The number of operations needed for range [left, right]
      // is derived from subtracting operations up to left - 1 from operations up to right.
      // Add 1 before dividing by 2 to handle odd values correctly.
      totalOperations += (getOperationsUpTo(right) - getOperationsUpTo(left - 1) + 1) / 2;
    }

    return totalOperations;
  }

  /**
   * Returns the number of operations required for range [1, n].
   * The operations are grouped in intervals of length powers of 4:
   * [1, 3], [4, 15], [16, 63], ... 
   * Each interval requires an increasing number of operations.
   */
  private long getOperationsUpTo(int n) {
    if (n <= 0) return 0; // Edge case: no operations if n is zero or negative

    long operationCount = 0;
    int currentOperationLevel = 0;

    // Iterate through powers of 4: 1, 4, 16, 64, ...
    for (int start = 1; start <= n; start *= 4) {
      final int rangeStart = start;
      final int rangeEnd = Math.min(n, start * 4 - 1);

      // Each "block" [rangeStart, rangeEnd] requires (currentOperationLevel + 1) operations
      operationCount += (long) (rangeEnd - rangeStart + 1) * ++currentOperationLevel;
    }

    return operationCount;
  }
}
