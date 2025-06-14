class Solution {
    public int minMaxDifference(int num) {
        String s = Integer.toString(num);
        
        // MAX: Replace first non-9 digit with 9
        char toReplaceMax = ' ';
        for (char c : s.toCharArray()) {
            if (c != '9') {
                toReplaceMax = c;
                break;
            }
        }
        String maxStr = toReplaceMax == ' ' ? s : s.replace(toReplaceMax, '9');
        
        // MIN: Replace first digit that is not 0 or equal to first digit with 0
        char toReplaceMin = s.charAt(0);
        String minStr = s.replace(toReplaceMin, '0');
        
        return Integer.parseInt(maxStr) - Integer.parseInt(minStr);
    }
}
