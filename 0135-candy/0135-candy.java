class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // Give each child at least one candy
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }

        // Left to right pass
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Right to left pass
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }

        // Sum the candies
        int minCandy = 0;
        for (int i = 0; i < n; i++) {
            minCandy += candies[i];
        }

        return minCandy;
    }
}
