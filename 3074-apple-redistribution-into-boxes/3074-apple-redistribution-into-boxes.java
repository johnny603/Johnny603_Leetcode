class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }

        Arrays.sort(capacity); // ascending
        int usedBoxes = 0;
        int currentCapacity = 0;

        // pick largest capacities first
        for (int i = capacity.length - 1; i >= 0; i--) {
            currentCapacity += capacity[i];
            usedBoxes++;
            if (currentCapacity >= totalApples) {
                return usedBoxes;
            }
        }

        return usedBoxes; // guaranteed possible by constraints
    }
}
