/* Each deci-binary number can contribute at most 1 to any digit position, so to build a digit d, you need at
least d numbers stacked
*/
class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;

        for (char c : n.toCharArray()) {
            maxDigit = Math.max(maxDigit, c - '0');
        }

        return maxDigit;
    }
}