import java.util.*;

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        // Count occurrences of each number
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        Set<Integer> seen = new HashSet<>();
        
        // Check for duplicate occurrence counts
        for (int freq : countMap.values()) {
            if (seen.contains(freq)) {
                return false;
            }
            seen.add(freq);
        }
        
        return true;
    }
}
