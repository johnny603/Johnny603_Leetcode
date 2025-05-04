class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] count = new int[100]; // because 1 <= a, b <= 9, so 10 * a + b is a unique key
        int result = 0;

        for (int[] domino : dominoes) {
            int a = domino[0];
            int b = domino[1];
            int key = a < b ? a * 10 + b : b * 10 + a;
            result += count[key]; // for every new domino, add how many we've already seen
            count[key]++;
        }

        return result;
    }
}
