class Solution {
    public int largestAltitude(int[] gain) {
        int max = 0;
        int current = 0;
        
        for (int i = 0; i < gain.length; i++) {
            current += gain[i];   // add the gain to the current altitude
            max = Math.max(max, current); // track the highest altitude
        }
        
        return max;
    }
}
