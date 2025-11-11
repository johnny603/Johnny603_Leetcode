class Solution {
  public int minOperations(int[] nums) {
    int ans = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(0); 
    // Start with 0 as the "ground level" — representing already-zeroed elements.

    for (final int num : nums) {
      // While the current number is smaller than the top of the stack,
      // we pop because those larger "levels" are now finished (we crossed them).
      // This ensures the stack is always in non-decreasing order.
      while (!stack.isEmpty() && stack.peek() > num)
        stack.pop();

      // If the current number introduces a *new* nonzero level (higher than last),
      // we need one more operation to eventually clear it.
      if (stack.isEmpty() || stack.peek() < num) {
        ++ans;          // New level encountered → one more operation
        stack.push(num); // Push this height to track it
      }
    }

    // The total number of times we push a new height equals
    // the total number of operations needed to bring everything to zero.
    return ans;
  }
}
