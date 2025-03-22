// uses recursion, backtracking and list

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }
    private void backtrack (List<String> result, String current, int open, int close, int max) {
        if(current.length() == max * 2) { // length of each string is 2n
            result.add(current);
            return;
        }
        if (open < max) { // Can still add an open bracket
            backtrack(result, current + "(", open + 1, close, max);
        }
        if (close < open) { // Can only close if open > close
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
}