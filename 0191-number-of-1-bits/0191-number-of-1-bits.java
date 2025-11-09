class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        String binaryString = Integer.toBinaryString(n);
        int x = binaryString.length();

        for (int i = 0; i < x; i++) {
            if (binaryString.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}