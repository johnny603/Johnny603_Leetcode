class Solution {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sorted = new ArrayList<>();
        inorder(root, sorted);
        return build(sorted, 0, sorted.size() - 1);
    }

    private void inorder(TreeNode node, List<Integer> sorted) {
        if (node == null) return;
        inorder(node.left, sorted);
        sorted.add(node.val);
        inorder(node.right, sorted);
    }

    private TreeNode build(List<Integer> arr, int l, int r) {
        if (l > r) return null;

        int mid = (l + r) / 2;
        TreeNode root = new TreeNode(arr.get(mid));

        root.left = build(arr, l, mid - 1);
        root.right = build(arr, mid + 1, r);

        return root;
    }
}
