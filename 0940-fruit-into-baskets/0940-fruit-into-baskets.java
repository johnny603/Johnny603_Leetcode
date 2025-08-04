// for all in fruits
// only two kinds of fruits can be chosen
// only move forward
// must pick at least one from every tree
// strategy: Use sliding window and a hashmap to keep track of amount of fruit
// we want the max amount of fruit
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>();
        int l = 0;
        int maxFruits = 0;

        for (int r = 0; r < fruits.length; r++) {
            // Add fruit at position r
            count.put(fruits[r], count.getOrDefault(fruits[r], 0) + 1);

            // Shrink window if more than 2 types
            while (count.size() > 2) {
                count.put(fruits[l], count.get(fruits[l]) - 1);
                if (count.get(fruits[l]) == 0) {
                    count.remove(fruits[l]);
                }
                l++;
            }

            // Update maxFruits
            maxFruits = Math.max(maxFruits, r - l + 1);
        }

        return maxFruits;
    }
}