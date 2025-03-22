class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>(); // Stack to hold opening brackets

        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // If the character is an opening bracket, push it to the stack
            if (currentChar == '(' || currentChar == '{' || currentChar == '[') {
                stack.push(currentChar);
            }
            // If the character is a closing bracket, check for matching opening bracket
            else {
                if (stack.isEmpty()) {
                    return false; // No matching opening bracket
                }

                char top = stack.pop(); // Pop the most recent opening bracket

                // Check if the top of the stack matches the current closing bracket
                if ((currentChar == ')' && top != '(') ||
                    (currentChar == '}' && top != '{') ||
                    (currentChar == ']' && top != '[')) {
                    return false; // Mismatch
                }
            }
        }

        // If the stack is empty, all brackets were matched
        return stack.isEmpty();
    }
}
