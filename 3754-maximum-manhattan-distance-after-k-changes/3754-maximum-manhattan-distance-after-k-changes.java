class Solution {
    // Store the movement sequence and allowed changes globally
    private char[] movementSequence;
    private int maxChangesAllowed;

    // Main function to return the maximum possible Manhattan distance
    public int maxDistance(String directions, int k) {
        // Convert the input string to a character array
        this.movementSequence = directions.toCharArray();
        this.maxChangesAllowed = k;

        // Try all significant direction combinations
        int distanceSouthEast = simulateMaxDistance('S', 'E');
        int distanceSouthWest = simulateMaxDistance('S', 'W');
        int distanceNorthEast = simulateMaxDistance('N', 'E');
        int distanceNorthWest = simulateMaxDistance('N', 'W');

        // Return the best possible distance from all attempts
        return Math.max(
            Math.max(distanceSouthEast, distanceSouthWest),
            Math.max(distanceNorthEast, distanceNorthWest)
        );
    }

    // Function to simulate travel using two chosen directions
    private int simulateMaxDistance(char preferredDir1, char preferredDir2) {
        int runningDistance = 0;      // Tracks the distance for the current path
        int bestDistance = 0;         // Stores the maximum distance achieved
        int usedChanges = 0;          // Counts how many direction changes have been used

        // Traverse the movement sequence one step at a time
        for (char currentDir : movementSequence) {
            // If the current direction is useful, move forward
            if (currentDir == preferredDir1 || currentDir == preferredDir2) {
                runningDistance++; // Step in a favorable direction
            }
            // If not, and we still have changes left, replace it
            else if (usedChanges < maxChangesAllowed) {
                runningDistance++;  // Pretend this was a good direction
                usedChanges++;      // Use up one of the allowed changes
            }
            // If no changes are left and it's a bad direction, it hurts our path
            else {
                runningDistance--;  // Step in an unfavorable direction
            }

            // Record the highest Manhattan distance seen so far
            bestDistance = Math.max(bestDistance, runningDistance);
        }

        // Return the best distance achieved with this pair of directions
        return bestDistance;
    }
}
