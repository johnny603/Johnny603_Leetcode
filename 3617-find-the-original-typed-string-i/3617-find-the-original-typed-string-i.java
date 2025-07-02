class Solution {
    public int possibleStringCount(String word) {
        Set<String> possible = new HashSet<>();
        possible.add(word);  // original string is always valid

        List<int[]> groups = new ArrayList<>();
        int n = word.length();

        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && word.charAt(j) == word.charAt(i)) {
                j++;
            }
            groups.add(new int[]{i, j}); // [start, end)
            i = j;
        }

        for (int[] group : groups) {
            int start = group[0], end = group[1];
            int len = end - start;

            if (len > 1) {
                // Try all shorter lengths from len - 1 down to 1
                for (int newLen = 1; newLen < len; newLen++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(word.substring(0, start));
                    for (int k = 0; k < newLen; k++) {
                        sb.append(word.charAt(start));
                    }
                    sb.append(word.substring(end));
                    possible.add(sb.toString());
                }
            }
        }

        return possible.size();
    }
}
