class Solution {

    // Segment tree that maintains prefix balance values
    struct SegmentTree {
        int size;

        // Each node stores:
        // mn = minimum prefix value in range
        // mx = maximum prefix value in range
        // lazy = pending range update
        struct Node {
            int mn, mx, lazy;
        };

        vector<Node> tree;

        SegmentTree(int n = 0) { init(n); }

        void init(int n) {
            size = n;
            tree.assign(4 * (size + 5), {0, 0, 0});
        }

        // Apply a value to a node (range increment)
        void apply(int node, int val) {
            tree[node].mn += val;
            tree[node].mx += val;
            tree[node].lazy += val;
        }

        // Push lazy updates to children
        void push(int node) {
            if (tree[node].lazy != 0) {
                apply(node << 1, tree[node].lazy);
                apply(node << 1 | 1, tree[node].lazy);
                tree[node].lazy = 0;
            }
        }

        // Recompute node from children
        void pull(int node) {
            tree[node].mn = min(tree[node << 1].mn, tree[node << 1 | 1].mn);
            tree[node].mx = max(tree[node << 1].mx, tree[node << 1 | 1].mx);
        }

        // Add val to range [L, R]
        void rangeAdd(int node, int left, int right, int L, int R, int val) {
            if (L > R || right < L || R < left) return;
            if (L <= left && right <= R) {
                apply(node, val);
                return;
            }
            push(node);
            int mid = (left + right) >> 1;
            rangeAdd(node << 1, left, mid, L, R, val);
            rangeAdd(node << 1 | 1, mid + 1, right, L, R, val);
            pull(node);
        }

        void rangeAdd(int L, int R, int val) {
            if (L <= R) rangeAdd(1, 0, size, L, R, val);
        }

        // Find first index in [L, R] whose value equals target
        int findFirstEqual(int node, int left, int right, int L, int R, int target) {
            if (L > R || right < L || R < left) return -1;
            if (tree[node].mn > target || tree[node].mx < target) return -1;
            if (left == right) return left;

            push(node);
            int mid = (left + right) >> 1;

            int res = findFirstEqual(node << 1, left, mid, L, R, target);
            if (res != -1) return res;
            return findFirstEqual(node << 1 | 1, mid + 1, right, L, R, target);
        }

        int findFirstEqual(int L, int R, int target) {
            return (L <= R) ? findFirstEqual(1, 0, size, L, R, target) : -1;
        }

        // Query value at a single index
        int pointQuery(int node, int left, int right, int idx) {
            if (left == right) return tree[node].mn;
            push(node);
            int mid = (left + right) >> 1;
            if (idx <= mid) return pointQuery(node << 1, left, mid, idx);
            return pointQuery(node << 1 | 1, mid + 1, right, idx);
        }

        int pointQuery(int idx) {
            return pointQuery(1, 0, size, idx);
        }
    };

public:
    int longestBalanced(vector<int>& nums) {
        int n = nums.size();
        SegmentTree seg(n);

        // Last occurrence of each value
        unordered_map<int, int> lastSeen;

        int best = 0;

        // r is 1-based prefix index
        for (int r = 1; r <= n; r++) {
            int value = nums[r - 1];
            int contribution = (value % 2 == 0) ? 1 : -1;

            int previousIndex = 0;
            if (lastSeen.count(value))
                previousIndex = lastSeen[value];

            // If value is new → add its contribution to all future prefixes
            if (previousIndex == 0) {
                seg.rangeAdd(r, n, contribution);
            }
            // If value repeats → remove its old contribution
            else {
                seg.rangeAdd(previousIndex, r - 1, -contribution);
            }

            lastSeen[value] = r;

            // Current prefix balance
            int currentBalance = seg.pointQuery(r);

            // Find earliest prefix with same balance
            int idx = seg.findFirstEqual(0, r - 1, currentBalance);

            if (idx != -1)
                best = max(best, r - idx);
        }

        return best;
    }
};
