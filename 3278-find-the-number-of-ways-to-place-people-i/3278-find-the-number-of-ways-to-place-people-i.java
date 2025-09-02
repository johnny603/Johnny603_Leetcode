class Solution {
    public int numberOfPairs(int[][] points) {
        // Sort points by x-coordinate in ascending order
        // If two points have the same x, sort by y-coordinate in descending order.
        // This ensures that for the same x, we only consider the "highest" point first,
        // which prevents counting invalid pairs later.
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
      
        int totalPairs = 0;         // Count of valid pairs
        int numPoints = points.length;
        final int NEG_INF = -(1 << 30); // Effectively a very small number, used to initialize maxY
      
        // Iterate through each point as a potential "left point" in the pair
        for (int leftIndex = 0; leftIndex < numPoints; ++leftIndex) {
            int leftY = points[leftIndex][1]; // y-coordinate of the left point
            int highestYSoFar = NEG_INF;      // Tracks the maximum y found that satisfies constraints
          
            // Check all points to the right of the current "left point"
            for (int rightIndex = leftIndex + 1; rightIndex < numPoints; ++rightIndex) {
                int rightY = points[rightIndex][1]; // y-coordinate of the right point
              
                // Conditions for a valid pair:
                // 1. The right point must have y <= left point's y (so it lies "underneath or equal").
                // 2. Among the right points we've seen so far, we only count this one if it's "higher"
                //    than all previously considered right points (prevents double-counting nested points).
                if (highestYSoFar < rightY && rightY <= leftY) {
                    highestYSoFar = rightY;  // Update the best (highest) candidate so far
                    totalPairs++;            // Found a valid pair
                }
            }
        }
        return totalPairs; // Return the final count of valid pairs
    }
}
