class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] result = new int[m * n];
        
        int r = 0, c = 0;  // starting point
        boolean up = true; // direction flag
        int idx = 0;
        
        while (idx < m * n) {
            result[idx++] = mat[r][c];
            
            if (up) { // moving up-right
                if (c == n - 1) { // hit right border
                    r++;
                    up = false;
                } else if (r == 0) { // hit top border
                    c++;
                    up = false;
                } else {
                    r--;
                    c++;
                }
            } else { // moving down-left
                if (r == m - 1) { // hit bottom border
                    c++;
                    up = true;
                } else if (c == 0) { // hit left border
                    r++;
                    up = true;
                } else {
                    r++;
                    c--;
                }
            }
        }
        return result;
    }
}
