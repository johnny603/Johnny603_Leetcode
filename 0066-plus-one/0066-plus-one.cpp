class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        int n = digits.size();
        
        // Traverse from the last digit and add 1
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;  // No carry, return result
            }
            digits[i] = 0;  // Set current digit to 0 and continue
        }
        
        // If we reach here, all digits were 9, so we need an extra digit at the front
        digits.insert(digits.begin(), 1);
        return digits;
    }
};
