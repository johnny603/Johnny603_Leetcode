class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] answer = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            
            // Count frequency in current subarray
            for (int j = i; j < i + k; j++) {
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            }

            // Sort entries by frequency desc, then value desc
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(freq.entrySet());
            list.sort((a, b) -> {
                if (!a.getValue().equals(b.getValue())) {
                    return b.getValue() - a.getValue(); // higher frequency first
                } else {
                    return b.getKey() - a.getKey(); // larger value first
                }
            });

            // Collect top x elements
            Set<Integer> topX = new HashSet<>();
            for (int j = 0; j < Math.min(x, list.size()); j++) {
                topX.add(list.get(j).getKey());
            }

            // Compute sum of subarray keeping only topX elements
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                if (topX.contains(nums[j])) {
                    sum += nums[j];
                }
            }

            answer[i] = sum;
        }

        return answer;
    }
}
