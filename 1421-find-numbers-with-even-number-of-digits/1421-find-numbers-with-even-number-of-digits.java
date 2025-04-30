class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            int digits = Integer.toString(num).length();
            if (digits % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
