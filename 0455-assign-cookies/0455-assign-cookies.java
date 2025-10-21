public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);  // sort greed factors
        Arrays.sort(s);  // sort cookie sizes
        
        int i = 0; // child pointer
        int j = 0; // cookie pointer
        
        // while there are children and cookies left
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) { // cookie satisfies this child's greed
                i++; // move to next child
            }
            j++; // move to next cookie
        }
        
        return i; // number of content children
    }
}