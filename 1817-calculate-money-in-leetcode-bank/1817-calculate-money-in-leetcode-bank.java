class Solution {
    public int totalMoney(int n) {
        int total = 0;
        int fullWeeks = n / 7;
        int remainingDays = n % 7;
        
        // Sum for full weeks
        // Each week starts with (1 + weekIndex)
        // Sum of a week = 7 * start + (0+1+2+3+4+5+6) = 7 * start + 21
        for (int i = 0; i < fullWeeks; i++) {
            total += 7 * (i + 1) + 21; 
        }
        
        // Sum for remaining days
        int start = fullWeeks + 1; // starting amount for the next week
        for (int d = 0; d < remainingDays; d++) {
            total += start + d;
        }
        
        return total;
    }
}
