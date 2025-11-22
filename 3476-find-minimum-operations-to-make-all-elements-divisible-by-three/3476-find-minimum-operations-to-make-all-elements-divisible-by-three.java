class Solution {
    public int minimumOperations(int[] nums) {
        int sol = 0;

        for (int x : nums) {
            int rem = x % 3;
            if (rem == 1 || rem == 2) {
                sol++;
            }
        }

        return sol;
    }
}
