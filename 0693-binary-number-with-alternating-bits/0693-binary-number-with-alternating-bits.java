class Solution {
    public boolean hasAlternatingBits(int n) {
        String binaryString = Integer.toBinaryString(n);
        int b = binaryString.length();


        for (int i = 1; i < b; i++) {
            if (binaryString.charAt(i) == binaryString.charAt(i - 1)) {
                return false;
            }
        }

        return true;
    }
}
