class Solution {
    public int binaryGap(int n) {
        String binary = Integer.toBinaryString(n);

        int prev = -1;   // index of previous '1'
        int maxGap = 0;

        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                if (prev != -1) {
                    maxGap = Math.max(maxGap, i - prev);
                }
                prev = i;
            }
        }

        return maxGap;
    }
}