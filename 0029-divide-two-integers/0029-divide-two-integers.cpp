class Solution {
public:
    int divide(int dividend, int divisor) {
        
        if (dividend == INT_MIN && divisor == -1) {
            return INT_MAX; // Clamp to the maximum 32-bit integer value
        }

        return dividend / divisor; // integers are naturally truncated to a whole number
        
    }
};