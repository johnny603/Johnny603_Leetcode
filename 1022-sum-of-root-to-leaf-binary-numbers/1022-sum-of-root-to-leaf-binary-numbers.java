class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int curr) {
        if (node == null) return 0;

        // Build binary number
        curr = (curr << 1) | node.val;

        // If leaf, return the number
        if (node.left == null && node.right == null) {
            return curr;
        }

        // Sum from left and right
        return dfs(node.left, curr) + dfs(node.right, curr);
    }
}