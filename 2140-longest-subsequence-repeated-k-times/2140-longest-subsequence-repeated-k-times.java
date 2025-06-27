class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
       
        // declare an array of 26 characters
        // count the frequency of each character
        // filter characters that appear k times
        // build strings lexicographically in decreasing order
        
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        List<Character> candidates = new ArrayList<>();
        for (int i = 25; i >= 0; i--) { // Start from 'z' for lexicographical order
            if (freq[i] >= k) {
                candidates.add((char)(i + 'a'));
            }
        }

        String result = "";
        Queue<String> queue = new LinkedList<>();
        queue.offer("");

                while (!queue.isEmpty()) {
            String curr = queue.poll();
            for (char c : candidates) {
                String next = curr + c;
                if (isKSubsequence(s, next, k)) {
                    if (next.length() > result.length() || 
                        (next.length() == result.length() && next.compareTo(result) > 0)) {
                        result = next;
                    }
                    queue.offer(next);
                }
            }
        }

        return result;    
    }

    private boolean isKSubsequence(String s, String target, int k) {
        int count = 0;
        int i = 0; // pointer for target

        for (char c : s.toCharArray()) {
            if (c == target.charAt(i)) {
                i++;
                if (i == target.length()) {
                    count++;
                    if (count == k)
                        return true;
                    i = 0; // reset to match again
                }
            }
        }

        return false;
    }
}