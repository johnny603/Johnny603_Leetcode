/*
FUNCTION numberOfPairs(points):

    # Step 1: Sort points
    # - Sort by x-coordinate in ascending order
    # - If x is the same, sort by y-coordinate in descending order
    SORT points BY (x ASC, y DESC)

    count ← 0
    numberOfPoints ← length of points
    NEG_INFINITY ← very small number

    # Step 2: Iterate over all points
    FOR i FROM 0 TO numberOfPoints - 1:
        y1 ← points[i].y
        maxY ← NEG_INFINITY

        # Step 3: Check subsequent points
        FOR j FROM i + 1 TO numberOfPoints - 1:
            y2 ← points[j].y

            # Step 4: Check if this forms a valid pair
            IF (maxY < y2 AND y2 ≤ y1):
                maxY ← y2
                count ← count + 1

    RETURN count
*/

class Solution {
    public int numberOfPairs(int[][] points) {
        // Sort the array of points in ascending order by x-coordinates,
        // and in descending order by y-coordinates if x-coordinates are the same
        Arrays.sort(points, (point1, point2) -> point1[0] == point2[0] ? point2[1] - point1[1] : point1[0] - point2[0]);
      
        int count = 0; // Initialize the count of valid pairs
        int numberOfPoints = points.length; // Total number of points
        final int INFINITY = 1 << 30; // Representation of negative infinity
      
        // Iterate over each point to check for valid pairs
        for (int i = 0; i < numberOfPoints; ++i) {
            int y1 = points[i][1]; // Get the y-coordinate of the current point

            int maxY = -INFINITY; // Set maxY as negative infinity initially

            // Iterate over the points that come after the current point
            for (int j = i + 1; j < numberOfPoints; ++j) {
                int y2 = points[j][1]; // Get the y-coordinate of the next point
              
                // Check if the current maxY is less than y2 and y2 is less than or equal to y1
                // If so, this forms a valid pair and update maxY and increment count
                if (maxY < y2 && y2 <= y1) {
                    maxY = y2; // Update maxY
                    ++count; // Increment the number of valid pairs
                }
            }
        }
      
        return count; // Return the total number of valid pairs
    }
}