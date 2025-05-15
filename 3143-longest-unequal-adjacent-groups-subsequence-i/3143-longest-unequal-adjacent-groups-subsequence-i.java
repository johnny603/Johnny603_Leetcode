class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> sol = new ArrayList<>();
        
        if (words.length == 0) return sol;
        
        sol.add(words[0]);
        int prevGroup = groups[0];
        
        for (int i = 1; i < words.length; i++) {
            if (groups[i] != prevGroup) {
                sol.add(words[i]);
                prevGroup = groups[i];
            }
        }

        return sol;          
    }
}
