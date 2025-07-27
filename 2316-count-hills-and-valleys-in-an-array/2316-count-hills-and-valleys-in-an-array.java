class Solution {
    public int countHillValley(int[] nums) {
        int count = 0;

        // Preprocess: remove consecutive duplicates
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                list.add(nums[i]);
            }
        }

        // Check for hills and valleys
        for (int i = 1; i < list.size() - 1; i++) {
            int prev = list.get(i - 1);
            int curr = list.get(i);
            int next = list.get(i + 1);

            if (curr > prev && curr > next) {
                count++; // Hill
            } else if (curr < prev && curr < next) {
                count++; // Valley
            }
        }

        return count;
    }
}
