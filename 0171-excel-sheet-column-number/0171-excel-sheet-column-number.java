class Solution {
    public int titleToNumber(String columnTitle) {
        int x = 0;
        char[] chars = columnTitle.toCharArray();
        for (char c : chars) {
            int value = c - 'A' + 1;
            x = x * 26 + value;
        }
        return x;
    }
}