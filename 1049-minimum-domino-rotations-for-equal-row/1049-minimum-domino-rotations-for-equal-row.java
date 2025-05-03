class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int result = check(tops[0], tops, bottoms);
        if (result != -1) return result;
        return check(bottoms[0], tops, bottoms);
    }

    private int check(int target, int[] tops, int[] bottoms) {
        int rotationsTop = 0;
        int rotationsBottom = 0;
        for (int i = 0; i < tops.length; i++) {
            if (tops[i] != target && bottoms[i] != target) {
                return -1; // Impossible
            } else if (tops[i] != target) {
                rotationsTop++; // Need to rotate to bring target to top
            } else if (bottoms[i] != target) {
                rotationsBottom++; // Need to rotate to bring target to bottom
            }
        }
        return Math.min(rotationsTop, rotationsBottom);
    }
}
