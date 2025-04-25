class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long count = 0;
        Map<Integer, Long> prefixModMap = new HashMap<>();
        prefixModMap.put(0, 1L); // Base case: empty prefix

        int prefixCount = 0;

        for (int num : nums) {
            if (num % modulo == k) {
                prefixCount++;
            }

            int mod = prefixCount % modulo;
            // Calculate target value we want from previous prefix
            int target = (mod - k + modulo) % modulo;

            count += prefixModMap.getOrDefault(target, 0L);

            // Update map with current prefix count mod
            prefixModMap.put(mod, prefixModMap.getOrDefault(mod, 0L) + 1);
        }

        return count;
    }
}
