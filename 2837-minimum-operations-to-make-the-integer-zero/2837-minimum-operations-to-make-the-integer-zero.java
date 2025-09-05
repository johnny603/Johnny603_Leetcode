class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (int k = 1; k <= 60; k++) { // integer i in the range [0, 60]
            long target = (long) num1 - (long) k * num2; // use long to avoid overflow
            
            if (target < k) continue; // cannot distribute into k parts
            
            int bitCount = Long.bitCount(target);
            
            if (bitCount <= k && k <= target) {
                return k;
            }
        }
        return -1;
    }
}
