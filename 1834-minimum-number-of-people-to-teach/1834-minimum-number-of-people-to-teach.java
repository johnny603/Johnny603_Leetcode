class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        
        // Convert each user's languages into a set for fast lookup
        List<Set<Integer>> userLangs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            Set<Integer> set = new HashSet<>();
            for (int lang : languages[i]) {
                set.add(lang);
            }
            userLangs.add(set);
        }
        
        // Step 1: Find users who cannot communicate with their friends
        Set<Integer> toTeach = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0] - 1; // users are 1-indexed
            int v = f[1] - 1;
            if (!canCommunicate(userLangs.get(u), userLangs.get(v))) {
                toTeach.add(u);
                toTeach.add(v);
            }
        }
        
        // If everyone already communicates
        if (toTeach.isEmpty()) return 0;
        
        // Step 2: Try each language as the "teaching language"
        int ans = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int count = 0;
            for (int user : toTeach) {
                if (!userLangs.get(user).contains(lang)) {
                    count++;
                }
            }
            ans = Math.min(ans, count);
        }
        
        return ans;
    }
    
    private boolean canCommunicate(Set<Integer> a, Set<Integer> b) {
        for (int lang : a) {
            if (b.contains(lang)) return true;
        }
        return false;
    }
}
