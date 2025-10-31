class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        
        // Count how many times each number appears
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Collect numbers that appear more than once
        List<Integer> sneakyList = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) > 1) {
                sneakyList.add(key);
            }
        }

        // Convert List<Integer> to int[]
        int[] sneaky = new int[sneakyList.size()];
        for (int i = 0; i < sneakyList.size(); i++) {
            sneaky[i] = sneakyList.get(i);
        }

        return sneaky;
    }
}
