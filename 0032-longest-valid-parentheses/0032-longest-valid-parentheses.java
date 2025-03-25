class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // Dummy index to handle base cases
        int maxLength = 0; // Track longest valid substring

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i); // Store the index of '('
            } else {
                stack.pop(); // Try to match with a previous '('
                if (stack.isEmpty()) {
                    stack.push(i); // Reset base index
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }
}
