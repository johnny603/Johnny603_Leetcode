/*
You always drink your initial numBottles.
For exchanging, you don’t need a loop.
The formula (numBottles - 1) / (numExchange - 1) works because:
Each new bottle costs numExchange empty bottles.
But when you drink that new bottle, you get one empty back.
So effectively, you only “lose” numExchange - 1 bottles per exchange.
Thus, you can exchange (numBottles - 1) / (numExchange - 1) times.

Example:
numBottles = 9, numExchange = 3
Initial: drink 9.
Exchanges: (9 - 1) / (3 - 1) = 8 / 2 = 4.
Total = 9 + 4 = 13
*/

class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        return numBottles + (numBottles - 1) / (numExchange - 1);
    }
}