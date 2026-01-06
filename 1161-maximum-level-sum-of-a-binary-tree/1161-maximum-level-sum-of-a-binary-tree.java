class Solution {
    public int maxLevelSum(TreeNode root) {
        List<Integer> levelSum = new ArrayList<>();
        dfs(root, 0, levelSum);

        int maxSum = Integer.MIN_VALUE;
        int level = 1;

        for (int i = 0; i < levelSum.size(); i++) {
            if (levelSum.get(i) > maxSum) {
                maxSum = levelSum.get(i);
                level = i + 1; // levels are 1-indexed
            }
        }

        return level;
    }

    private void dfs(TreeNode node, int depth, List<Integer> levelSum) {
        if (node == null) return;

        if (depth == levelSum.size()) {
            levelSum.add(node.val);
        } else {
            levelSum.set(depth, levelSum.get(depth) + node.val);
        }

        dfs(node.left, depth + 1, levelSum);
        dfs(node.right, depth + 1, levelSum);
    }
}
