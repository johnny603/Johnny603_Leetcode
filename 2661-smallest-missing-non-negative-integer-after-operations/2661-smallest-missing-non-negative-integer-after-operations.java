class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> freq = new HashMap<>();

        // Count how many numbers fall into each remainder group
        for (int i = 0; i < nums.length; i++) {
            int mod = ((nums[i] % value) + value) % value; // to handle negative numbers correctly
            freq.put(mod, freq.getOrDefault(mod, 0) + 1);
        }

        int i = 0;
        while (true) {
            int mod = i % value;
            if (!freq.containsKey(mod) || freq.get(mod) == 0) {
                return i;
            }
            freq.put(mod, freq.get(mod) - 1);
            i++;
        }
    }
}
