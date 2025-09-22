class Solution {
    public int maxFrequencyElements(int[] nums) {
        // Step 1: Count frequencies (nums[i] is between 1 and 100, so we can use an array)
        int[] freq = new int[101];  
        for (int num : nums) {
            freq[num]++;
        }

        // Step 2: Find maximum frequency
        int maxFreq = 0;
        for (int f : freq) {
            if (f > maxFreq) {
                maxFreq = f;
            }
        }

        // Step 3: Count how many elements have this max frequency
        int count = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                count++;
            }
        }

        // Step 4: Return total occurrences
        return count * maxFreq;
    }
}
