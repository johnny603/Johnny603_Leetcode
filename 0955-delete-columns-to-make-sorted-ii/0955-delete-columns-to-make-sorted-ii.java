class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs.length;
        int n = strs[0].length();
        int deletions = 0;

        boolean[] sorted = new boolean[m - 1];

        for (int col = 0; col < n; col++) {
            boolean bad = false;

            // check if column breaks order
            for (int i = 0; i < m - 1; i++) {
                if (!sorted[i] && strs[i].charAt(col) > strs[i + 1].charAt(col)) {
                    bad = true;
                    break;
                }
            }

            if (bad) {
                deletions++;
                continue; // delete this column
            }

            // update sorted pairs
            for (int i = 0; i < m - 1; i++) {
                if (!sorted[i] && strs[i].charAt(col) < strs[i + 1].charAt(col)) {
                    sorted[i] = true;
                }
            }
        }

        return deletions;
    }
}
