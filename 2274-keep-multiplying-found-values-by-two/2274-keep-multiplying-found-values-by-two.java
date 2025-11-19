class Solution {
    public int findFinalValue(int[] nums, int original) {

        boolean found = true;

        while (found) {
            found = false;   // reset before scanning

            for (int num : nums) {
                if (num == original) {
                    original *= 2;
                    found = true;
                    break;  // restart scanning from the beginning
                }
            }
        }

        return original;
    }
}
