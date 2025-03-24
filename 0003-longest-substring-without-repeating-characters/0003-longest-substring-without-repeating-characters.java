class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Base cases
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int x = 0;  // Tracks max substring length
        int left = 0;  
        Map<Character, Integer> map = new HashMap<>(); // Stores character positions
        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                // Move left pointer to exclude duplicate character
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            // Add/update character in map
            map.put(s.charAt(right), right);
            // Update max length
            x = Math.max(x, right - left + 1);
        }
        return x;
    }
}
