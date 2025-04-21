class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long sum = 0;
        long minPrefix = 0;
        long maxPrefix = 0;
        
        for (int diff : differences) {
            sum += diff;
            minPrefix = Math.min(minPrefix, sum);
            maxPrefix = Math.max(maxPrefix, sum);
        }
        
        long minStart = lower - minPrefix;
        long maxStart = upper - maxPrefix;
        
        long count = maxStart - minStart + 1;
        return (int)Math.max(0, count);
    }
}
