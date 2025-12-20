class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs.length;         // number of rows
        int n = strs[0].length();    // number of columns
        int deleteCount = 0;

        for (int col = 0; col < n; col++) {
            for (int row = 1; row < m; row++) {
                if (strs[row].charAt(col) < strs[row - 1].charAt(col)) {
                    deleteCount++;
                    break; // no need to check rest of this column
                }
            }
        }

        return deleteCount;
    }
}
