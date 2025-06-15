class Solution {
    public int maxDiff(int num) {
        String s = Integer.toString(num);
        
        // Maximize: replace first non-'9' digit with '9'
        char maxDigit = ' ';
        for (char c : s.toCharArray()) {
            if (c != '9') {
                maxDigit = c;
                break;
            }
        }
        
        String aStr;
        if (maxDigit == ' ') {
            aStr = s;
        } else {
            aStr = s.replace(maxDigit, '9');
        }
        int a = Integer.parseInt(aStr);

        // Minimize: 
        // If the first digit is not '1', replace it with '1'
        // Otherwise, find the first digit (after the first one) that is not '0' or '1', and replace it with '0'
        char minDigit = s.charAt(0);
        String bStr;

        if (minDigit != '1') {
            bStr = s.replace(minDigit, '1');
        } else {
            minDigit = ' ';
            for (int i = 1; i < s.length(); i++) {
                char current = s.charAt(i);
                if (current != '0' && current != '1') {
                    minDigit = current;
                    break;
                }
            }
            if (minDigit == ' ') {
                bStr = s;
            } else {
                bStr = s.replace(minDigit, '0');
            }
        }

        int b = Integer.parseInt(bStr);

        return Math.abs(a - b);
    }
}
