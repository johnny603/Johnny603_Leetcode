class Solution {
    Map<String, List<Character>> map = new HashMap<>();
    Map<String, Boolean> memo = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // Build mapping: "AB" -> ['C', 'D', ...]
        for (String s : allowed) {
            String key = s.substring(0, 2);
            char top = s.charAt(2);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(top);
        }

        return dfs(bottom);
    }

    private boolean dfs(String row) {
        // Base case
        if (row.length() == 1) return true;

        if (memo.containsKey(row)) return memo.get(row);

        // Generate all possible next rows
        List<String> nextRows = new ArrayList<>();
        buildNextRows(row, 0, new StringBuilder(), nextRows);

        for (String next : nextRows) {
            if (dfs(next)) {
                memo.put(row, true);
                return true;
            }
        }

        memo.put(row, false);
        return false;
    }

    private void buildNextRows(String row, int index, StringBuilder sb, List<String> result) {
        if (index == row.length() - 1) {
            result.add(sb.toString());
            return;
        }

        String key = row.substring(index, index + 2);
        if (!map.containsKey(key)) return;

        for (char c : map.get(key)) {
            sb.append(c);
            buildNextRows(row, index + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
