class Solution {
    private int mountainHeight;
    private int[] workerTimes;

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        this.mountainHeight = mountainHeight;
        this.workerTimes = workerTimes;

        // Binary search bounds
        long left = 1;                // Minimum possible time
        long right = (long) 1e16;     // Upper bound large enough to guarantee feasibility
        long minTimeRequired = -1;    // Will store the answer

        while (left <= right) {
            long midTime = left + (right - left) / 2;

            if (canReduceMountainInTime(midTime)) {
                // If possible to reduce mountain in midTime, try a smaller time
                minTimeRequired = midTime;
                right = midTime - 1;
            } else {
                // If not possible, we need more time
                left = midTime + 1;
            }
        }

        return minTimeRequired;
    }

    /**
     * Determines how much mountain height each worker can reduce within the given time limit.
     * Uses the formula for sum of first x integers: sum = x * (x + 1) / 2.
     *
     * @param timeLimit Maximum allowed time in seconds
     * @return true if total reduction >= mountain height
     */
    private boolean canReduceMountainInTime(long timeLimit) {
        long totalHeightReduced = 0;

        for (int timePerUnit : workerTimes) {
            // Solve for max height worker can reduce in timeLimit:
            //  timeLimit >= timePerUnit * (1 + 2 + ... + x) = timePerUnit * x * (x + 1) / 2
            // Solve quadratic: x^2 + x - 2 * timeLimit / timePerUnit <= 0
            // Quadratic solution: x = (-1 + sqrt(1 + 8*timeLimit/timePerUnit)) / 2
            long maxHeightByThisWorker = (long) (Math.sqrt(2.0 * timeLimit / timePerUnit + 0.25) - 0.5);

            totalHeightReduced += maxHeightByThisWorker;
        }

        // Check if combined workers can reduce the mountain fully
        return totalHeightReduced >= mountainHeight;
    }
}