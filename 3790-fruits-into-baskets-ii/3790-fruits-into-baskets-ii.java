/*
Iterate through each fruit.
For each fruit, iterate through the baskets from left to right.
If you find a basket that:
Has not already been used, and
Has enough capacity for the fruit,
Then mark that basket as used and break (move on to the next fruit).
If no basket is found for a fruit, increment a counter for unplaced fruits.
*/


class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] used = new boolean[n];  // To track if a basket has been used
        int unplaced = 0;

        for (int i = 0; i < n; i++) {
            boolean placed = false;
            for (int j = 0; j < n; j++) {
                if (!used[j] && baskets[j] >= fruits[i]) {
                    used[j] = true;
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                unplaced++;
            }
        }

        return unplaced;
    }
}
