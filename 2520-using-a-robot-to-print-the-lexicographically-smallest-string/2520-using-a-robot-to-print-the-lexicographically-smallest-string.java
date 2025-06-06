class Solution {
    public String robotWithString(String s) {
        int[] freq = new int[26]; // frequency of each char in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder t = new StringBuilder(); // robot's internal string
        StringBuilder paper = new StringBuilder(); // final result

        int minChar = 0; // track smallest char left in s

        for (char c : s.toCharArray()) {
            t.append(c);
            freq[c - 'a']--;

            // update minChar to reflect current smallest in s
            while (minChar < 26 && freq[minChar] == 0) {
                minChar++;
            }

            // move from t to paper if top of t <= minChar
            while (t.length() > 0 && (minChar == 26 || t.charAt(t.length() - 1) <= (char) (minChar + 'a'))) {
                paper.append(t.charAt(t.length() - 1));
                t.setLength(t.length() - 1);
            }
        }

        return paper.toString();
    }
}
