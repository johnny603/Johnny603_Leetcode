class Solution {

    public String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet<>(Arrays.asList(nums));
        return backtrack(set, new StringBuilder(), nums.length);
    }

    private String backtrack(Set<String> set, StringBuilder curr, int n) {

        if (curr.length() == n) {
            String candidate = curr.toString();
            if (!set.contains(candidate)) {
                return candidate;
            }
            return null;
        }

        curr.append('0');
        String res = backtrack(set, curr, n);
        if (res != null) return res;
        curr.deleteCharAt(curr.length() - 1);

        curr.append('1');
        res = backtrack(set, curr, n);
        if (res != null) return res;
        curr.deleteCharAt(curr.length() - 1);

        return null;
    }
}