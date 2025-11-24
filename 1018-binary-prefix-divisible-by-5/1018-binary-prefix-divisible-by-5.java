class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>();
        int x = 0;  // keep only modulo 5 value

        for (int bit : nums) {
            x = ((x << 1) | bit) % 5;  // bit manipulation + modulo
            result.add(x == 0);
        }

        return result;
    }
}
