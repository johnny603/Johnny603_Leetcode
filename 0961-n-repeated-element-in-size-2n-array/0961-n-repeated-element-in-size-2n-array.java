class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length / 2;
        HashSet<Integer> seen = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (!seen.add(nums[i])) {
                return nums[i]; // first duplicate must be the answer
            }
        }

        return -1; // never reached
    }
}
