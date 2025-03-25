class Solution {
public:
    int mySqrt(int x) {
        if (x < 2) return x; // Base case
        
        int low = 1, high = x;
        while (low <= high) {
            long long mid = low + (high - low) / 2; // Prevent overflow
            long long square = mid * mid; // Avoid overflow
            
            if (square == x) {
                return mid;
            } else if (square < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high; // Return the floor of sqrt(x)
    }
};
