class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1"; // base case
        }
        String prev = countAndSay(n - 1); // recurse
        StringBuilder result = new StringBuilder(); // append countAndSay to a new string
        int count = 1; 

        for (int i = 1; i < prev.length(); i++) {
            if (prev.charAt(i) == prev.charAt(i - 1)) {
                count++;
            } else {
                result.append(count).append(prev.charAt(i - 1));
                count = 1;
            }
        }

        result.append(count).append(prev.charAt(prev.length() - 1));
        return result.toString();

    }
}