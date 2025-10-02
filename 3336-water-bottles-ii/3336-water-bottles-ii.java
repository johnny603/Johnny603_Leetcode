class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int total = numBottles;     // drink initial bottles
        int empties = numBottles;   // track empty bottles

        while (empties >= numExchange) {
            // exchange empties for 1 full bottle
            empties -= numExchange;
            total += 1;     // drink it
            empties += 1;   // gain back empty after drinking

            numExchange++;  // exchange cost increases
        }

        return total;
    }
}
