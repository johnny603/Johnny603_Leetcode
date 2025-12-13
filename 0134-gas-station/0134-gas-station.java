class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;    // total gas left after completing the circle
        int currTank = 0;     // gas in tank for the current attempt
        int startStation = 0; // candidate starting station

        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            totalTank += diff;
            currTank += diff;

            // If current tank is negative, can't start from 'startStation'
            if (currTank < 0) {
                startStation = i + 1; // next station is new candidate
                currTank = 0;         // reset tank for new start
            }
        }

        return totalTank >= 0 ? startStation : -1;
    }
}
