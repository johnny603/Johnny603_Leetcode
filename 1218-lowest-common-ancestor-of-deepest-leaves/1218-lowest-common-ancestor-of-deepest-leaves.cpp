/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    // Helper function to return both depth and LCA
    pair<int, TreeNode*> dfs(TreeNode* node) {
        if (node == nullptr) return make_pair(0, (TreeNode*)nullptr);

        pair<int, TreeNode*> left = dfs(node->left);
        pair<int, TreeNode*> right = dfs(node->right);

        // Compare depths of left and right subtrees
        if (left.first > right.first) {
            return make_pair(left.first + 1, left.second);
        } else if (right.first > left.first) {
            return make_pair(right.first + 1, right.second);
        } else {
            // Equal depths: current node is LCA
            return make_pair(left.first + 1, node);
        }
    }

    TreeNode* lcaDeepestLeaves(TreeNode* root) {
        pair<int, TreeNode*> result = dfs(root);
        return result.second;
    }
};
