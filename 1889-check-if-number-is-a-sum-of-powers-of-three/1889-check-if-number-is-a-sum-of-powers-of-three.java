class Solution {
    public boolean checkPowersOfThree(int n) {
        // base 3 check
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3; // continue throughout all integers
        }
        return true;
    }
}