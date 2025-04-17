// constraint is small so we can brute force this

class Solution {
    public int countPairs(int[] nums, int k) {
        int pair = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) { // i < j
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    pair++;
                }
            }
        }
        return pair;
    }
}