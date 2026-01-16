class Solution:
    def generateTrees(self, n: int) -> int:
        if n == 0:
            return []

        # dp[i] = list of all unique BSTs with i nodes
        dp = [[] for _ in range(n + 1)]
        dp[0] = [None]  # empty tree

        for nodes in range(1, n + 1):
            for root in range(1, nodes + 1):
                left_trees = dp[root - 1]
                right_trees = dp[nodes - root]

                for left in left_trees:
                    for right in right_trees:
                        root_node = TreeNode(root)
                        root_node.left = left
                        root_node.right = self.clone(right, root)
                        dp[nodes].append(root_node)

        return dp[n]

    def clone(self, node, offset):
        """Clone a tree and add offset to all node values"""
        if not node:
            return None
        new_node = TreeNode(node.val + offset)
        new_node.left = self.clone(node.left, offset)
        new_node.right = self.clone(node.right, offset)
        return new_node
