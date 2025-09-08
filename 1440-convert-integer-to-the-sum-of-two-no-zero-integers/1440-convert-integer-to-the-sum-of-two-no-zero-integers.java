class Solution {
    public int[] getNoZeroIntegers(int n) {
        int[] result = new int[2];

        // Try all possible values of a
        for (int a = 1; a < n; a++) {
            int b = n - a;
            // If both numbers do not contain zero, return them
            if (isValid(a) && isValid(b)) {
                result[0] = a;
                result[1] = b;
                return result;
            }
        }

        return result; // just in case, though guaranteed solution exists
    }

    // Helper to check if number contains '0'
    private boolean isValid(int num) {
        while (num > 0) {
            if (num % 10 == 0) return false;
            num /= 10;
        }
        return true;
    }
}
