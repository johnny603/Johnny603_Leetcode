class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int[] answer = new int[nums.size()];

        for (int i = 0; i < nums.size(); i++) {
            int n = nums.get(i);

            // If n is even, impossible
            if ((n & 1) == 0) {
                answer[i] = -1;
                continue;
            }

            // Count trailing ones
            int count = 0;
            int temp = n;
            while ((temp & 1) == 1) {
                count++;
                temp >>= 1;
            }

            // Subtract 2^(count-1)
            answer[i] = n - (1 << (count - 1));
        }

        return answer;
    }
}
