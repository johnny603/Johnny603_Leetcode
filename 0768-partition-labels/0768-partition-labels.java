class Solution {
    public List<Integer> partitionLabels(String s) {
        // Store the last occurrence index of each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        List<Integer> result = new ArrayList<>();
        int start = 0;  // Start index of current partition
        int end = 0;    // End index of current partition
        
        // Iterate through the string
        for (int i = 0; i < s.length(); i++) {
            // Update the end index based on the furthest last occurrence
            end = Math.max(end, lastIndex[s.charAt(i) - 'a']);
            
            // If we've reached the end of the current partition
            if (i == end) {
                // Add the length of this partition to our result
                result.add(end - start + 1);
                // Update the start for the next partition
                start = i + 1;
            }
        }
        return result;
    }
}


// take in string
// define a substring by these rules
// notice how the letters are in order
// once a letter has been assigned, it cannot be used again
// Example 1:
// ababccdd
// abab. cc, dd
// Example 2:
// abcdfefe
// abcd, fefe

// Idea: we need to loop through the string once, if a character equals the next, append to new partition, count the length of each string
