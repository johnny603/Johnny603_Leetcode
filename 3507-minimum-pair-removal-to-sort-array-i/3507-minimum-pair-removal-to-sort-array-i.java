class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }

        int count = 0;

        while (!isNonDecreasing(arr)) {
            int minSum = Integer.MAX_VALUE;
            int index = 0;

            // Find adjacent pair with minimum sum (leftmost if tie)
            for (int i = 0; i < arr.size() - 1; i++) {
                int sum = arr.get(i) + arr.get(i + 1);
                if (sum < minSum) {
                    minSum = sum;
                    index = i;
                }
            }

            // Replace the pair with their sum
            arr.remove(index + 1);
            arr.set(index, minSum);

            count++;
        }

        return count;
    }

    private boolean isNonDecreasing(List<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < arr.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
