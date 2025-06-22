class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        int parts = (n + k - 1) / k;  // number of chunks needed
        
        String[] result = new String[parts];
        
        for (int i = 0; i < parts; i++) {
            int start = i * k;
            int end = Math.min(start + k, n);
            String chunk = s.substring(start, end);
            
            // If last chunk is smaller than k, pad with fill character
            if (chunk.length() < k) {
                StringBuilder sb = new StringBuilder(chunk);
                while (sb.length() < k) {
                    sb.append(fill);
                }
                chunk = sb.toString();
            }
            
            result[i] = chunk;
        }
        
        return result;
    }
}
