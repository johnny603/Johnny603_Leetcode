// Brute force to optimize

// go through all of nums

// if nums[i] and nums[i+1] are non coprime, delete the two numbers and replace them with their LCM (Least Common Multiple)

// Least common multiple is the the smallest positive integer that is divisible by both num a and b

// if no coprimes are found break out of the loop

// repeat this loop until there are no more non coprimes

// The greatest common divisor (GCD), or greatest common factor (GCF), is the largest positive integer that divides two or more numbers without leaving a remainder

// we want to return the final array after the loop terminates

/* Example:
Input: nums = [6,4,3,2,7,6,2]
Output: [12,7,6]
Explanation: 
- (6, 4) are non-coprime with LCM(6, 4) = 12. Now, nums = [12,3,2,7,6,2].
- (12, 3) are non-coprime with LCM(12, 3) = 12. Now, nums = [12,2,7,6,2].
- (12, 2) are non-coprime with LCM(12, 2) = 12. Now, nums = [12,7,6,2].
- (6, 2) are non-coprime with LCM(6, 2) = 6. Now, nums = [12,7,6].
There are no more adjacent non-coprime numbers in nums.
Thus, the final modified array is [12,7,6].
Note that there are other ways to obtain the same resultant array.
*/

class Solution {
    /**
     * Replaces adjacent non-coprime numbers with their LCM until no adjacent pairs share a common factor > 1
     * @param nums Input array of positive integers
     * @return List of integers after all possible replacements
     */
    public List<Integer> replaceNonCoprimes(int[] nums) {
        // Use a list as a stack to store the result
        List<Integer> stack = new ArrayList<>();
      
        // Process each number in the input array
        for (int currentNum : nums) {
            // Add current number to the stack
            stack.add(currentNum);
          
            // Keep merging adjacent non-coprime numbers
            while (stack.size() > 1) {
                // Get the last two elements from the stack
                int lastElement = stack.get(stack.size() - 1);
                int secondLastElement = stack.get(stack.size() - 2);
              
                // Calculate GCD of the two elements
                int gcdValue = gcd(lastElement, secondLastElement);
              
                // If they are coprime (GCD = 1), no merge needed
                if (gcdValue == 1) {
                    break;
                }
              
                // Remove the last element
                stack.remove(stack.size() - 1);
              
                // Replace the second last element with LCM of both elements
                // LCM(a, b) = (a * b) / GCD(a, b)
                // Using long to prevent integer overflow during multiplication
                int lcmValue = (int) ((long) lastElement * secondLastElement / gcdValue);
                stack.set(stack.size() - 1, lcmValue);
            }
        }
      
        return stack;
    }

    /**
     * Calculates the Greatest Common Divisor using Euclidean algorithm
     * @param a First positive integer
     * @param b Second positive integer
     * @return GCD of a and b
     */
    private int gcd(int a, int b) {
        // Base case: when b becomes 0, a is the GCD
        if (b == 0) {
            return a;
        }
        // Recursive case: GCD(a, b) = GCD(b, a mod b)
        return gcd(b, a % b);
    }
}
