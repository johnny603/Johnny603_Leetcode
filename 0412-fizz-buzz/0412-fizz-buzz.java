/*
For 1 to n
if a number is divisible by 3, print Fizz
if a number is divisible by 5, print Buzz
if a number if divisible by BOTH 3 and 5, print FizzBuzz
if no conditions match above, print the number
*/

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> sol = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                sol.add("FizzBuzz");
            }
            else if (i % 3 == 0) {
                sol.add("Fizz");
            }
            else if (i % 5 == 0) {
                sol.add("Buzz");
            } else {
                sol.add(String.valueOf(i));
            }
        }

        return sol;
    }
}