class Solution {
    public int countTriples(int n) {
        int count = 0;

        // enumerate a and b
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                int c2 = a * a + b * b;
                int c = (int)Math.sqrt(c2);

                // check if c is valid
                if (c <= n && c * c == c2) {
                    count++;
                }
            }
        }

        return count;
    }
}
