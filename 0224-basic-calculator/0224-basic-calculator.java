class Solution {
    public int calculate(String s) {
        int total = 0;
        int num = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                total += sign * num;
                num = 0;
                sign = 1;
            } else if (c == '-') {
                total += sign * num;
                num = 0;
                sign = -1;
            } else if (c == '(') {
                // Push the current total and sign
                stack.push(total);
                stack.push(sign);
                // Reset for the new sub-expression inside parentheses
                total = 0;
                sign = 1;
            } else if (c == ')') {
                total += sign * num;  // Add the last number before ')'
                num = 0;

                int prevSign = stack.pop();  // sign before '('
                int prevTotal = stack.pop(); // total before '('

                total = prevTotal + prevSign * total;  // Combine
            } else if (c == ' ') {
                // Skip spaces
                continue;
            }
        }

        total += sign * num;  // Add any remaining number
        return total;
    }
}
