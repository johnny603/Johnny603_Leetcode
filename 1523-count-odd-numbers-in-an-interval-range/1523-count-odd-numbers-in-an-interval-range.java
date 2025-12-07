/*
Brute force


class Solution {
    public int countOdds(int low, int high) {
        int count = 0;
        while (low <= high) {
            if (low % 2 == 1) { // odd
                count++;
            }
            low++;
        }
        return count;
    }
}
*/


class Solution {
    public int countOdds(int low, int high) {
        return (high + 1) / 2 - low / 2;
    }
}
