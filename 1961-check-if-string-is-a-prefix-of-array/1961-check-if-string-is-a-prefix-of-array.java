class Solution {
    public boolean isPrefixString(String s, String[] words) {
        int i = 0;  // pointer in s

        for (String w : words) {
            for (char c : w.toCharArray()) {
                // s finished but we still have characters in w
                if (i == s.length()) return false;

                // mismatch
                if (s.charAt(i) != c) return false;

                i++; // move through s
            }

            // if we matched entire s
            if (i == s.length()) return true;
        }

        return false; // used all prefix words but s wasn't finished
    }
}
