class Solution(object):
    def isValid(self, s):
        stack = []
        mapping = {')': '(', ']': '[', '}': '{'}

        for char in s:
            if char in mapping.values():
                # Opening bracket, push onto stack
                stack.append(char)
            elif char in mapping:
                # Closing bracket, check stack top
                if not stack or stack.pop() != mapping[char]:
                    return False
            else:
                # Invalid character (optional)
                return False

        return not stack  # True if stack is empty (all matched)
