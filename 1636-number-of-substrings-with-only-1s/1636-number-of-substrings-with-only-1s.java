class Solution {
    public int numSub(String s) {
        long result = 0;
        long MOD = 1_000_000_007;
        int n = s.length();
        
        int l = 0;  // left pointer
        
        while (l < n) {
            // Skip zeros
            if (s.charAt(l) == '0') {
                l++;
                continue;
            }
            
            // Now s[l] == '1', find the end of this 1-block
            int r = l;
            while (r < n && s.charAt(r) == '1') {
                r++;
            }
            
            // Length of the block of consecutive 1s
            long len = r - l;
            
            // Apply formula for number of substrings in this block
            result += len * (len + 1) / 2;
            result %= MOD;
            
            // Move l to after this block
            l = r;
        }
        
        return (int)result;
    }
}
