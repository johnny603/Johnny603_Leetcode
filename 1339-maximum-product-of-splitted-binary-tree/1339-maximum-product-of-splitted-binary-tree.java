class Solution {
    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // 1) First DFS: get total sum
        totalSum = getTotalSum(root);

        // 2) Second DFS: compute subtree sums & products
        computeSubtreeSum(root);

        return (int)(maxProduct % MOD);
    }

    // First DFS
    private long getTotalSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getTotalSum(node.left) + getTotalSum(node.right);
    }

    // Second DFS (postorder)
    private long computeSubtreeSum(TreeNode node) {
        if (node == null) return 0;

        long left = computeSubtreeSum(node.left);
        long right = computeSubtreeSum(node.right);

        long subSum = node.val + left + right;

        // product if we cut above this subtree
        long product = subSum * (totalSum - subSum);
        maxProduct = Math.max(maxProduct, product);

        return subSum;
    }
}
