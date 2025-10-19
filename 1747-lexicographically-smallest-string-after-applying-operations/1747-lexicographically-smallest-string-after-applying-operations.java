class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        String smallest = s;
        
        queue.offer(s);
        seen.add(s);
        
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.compareTo(smallest) < 0) {
                smallest = curr;
            }
            
            // Add a to all odd indices
            String added = addOperation(curr, a);
            if (seen.add(added)) {
                queue.offer(added);
            }
            
            // Rotate right by b
            String rotated = rotateOperation(curr, b);
            if (seen.add(rotated)) {
                queue.offer(rotated);
            }
        }
        
        return smallest;
    }
    
    private String addOperation(String s, int a) {
        char[] chars = s.toCharArray();
        for (int i = 1; i < s.length(); i += 2) {
            int newDigit = (chars[i] - '0' + a) % 10;
            chars[i] = (char) ('0' + newDigit);
        }
        return new String(chars);
    }
    
    private String rotateOperation(String s, int b) {
        int n = s.length();
        return s.substring(n - b) + s.substring(0, n - b);
    }
}
