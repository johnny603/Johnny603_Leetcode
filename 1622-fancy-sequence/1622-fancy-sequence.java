/**
 * TreeNode represents a node inside the segment tree.
 *
 * Each node stores:
 * - the range it represents
 * - the sum of that range
 * - lazy operations that still need to be applied to children
 */
class TreeNode {

    TreeNode leftChild;
    TreeNode rightChild;

    int startIndex;
    int endIndex;
    int midpoint;

    long segmentSum;

    // lazy operations waiting to propagate
    long pendingAdd;
    long pendingMultiply = 1;

    public TreeNode(int start, int end) {
        this.startIndex = start;
        this.endIndex = end;
        this.midpoint = (start + end) >> 1;
    }
}


/**
 * SegmentTree handles:
 * - range addition
 * - range multiplication
 * - range sum queries
 *
 * Lazy propagation allows updates without visiting every element.
 */
class SegmentTree {

    private TreeNode root = new TreeNode(1, (int)1e5 + 1);

    private static final int MOD = (int)1e9 + 7;

    /*------------------------------------------------------------
        RANGE ADD
    ------------------------------------------------------------*/

    public void addRange(int left, int right, int increment) {
        addRange(left, right, increment, root);
    }

    private void addRange(int left, int right, int increment, TreeNode node) {

        if (left > right) return;

        // If node fully inside update range
        if (node.startIndex >= left && node.endIndex <= right) {

            /**
             Example:
             Node range = [3,5]
             Current values = [10,20,30]
             Add 5 to each -> [15,25,35]

             Sum increases by:
             length * increment
             = 3 * 5
             */

            node.segmentSum =
                    (node.segmentSum +
                    (long)(node.endIndex - node.startIndex + 1) * increment) % MOD;

            node.pendingAdd =
                    (node.pendingAdd + increment) % MOD;

            return;
        }

        pushLazyToChildren(node);

        if (left <= node.midpoint)
            addRange(left, right, increment, node.leftChild);

        if (right > node.midpoint)
            addRange(left, right, increment, node.rightChild);

        updateFromChildren(node);
    }


    /*------------------------------------------------------------
        RANGE MULTIPLY
    ------------------------------------------------------------*/

    public void multiplyRange(int left, int right, int multiplier) {
        multiplyRange(left, right, multiplier, root);
    }

    private void multiplyRange(int left, int right, int multiplier, TreeNode node) {

        if (left > right) return;

        if (node.startIndex >= left && node.endIndex <= right) {

            /**
             Example:
             values = [5,7]
             multiply by 2 -> [10,14]

             segmentSum must also double
             */

            node.segmentSum =
                    (node.segmentSum * multiplier) % MOD;

            /**
             Important:
             multiplication must affect pending additions too.

             Example:
             pendingAdd = +3
             multiply by 2 -> future add becomes +6
             */

            node.pendingAdd =
                    (node.pendingAdd * multiplier) % MOD;

            node.pendingMultiply =
                    (node.pendingMultiply * multiplier) % MOD;

            return;
        }

        pushLazyToChildren(node);

        if (left <= node.midpoint)
            multiplyRange(left, right, multiplier, node.leftChild);

        if (right > node.midpoint)
            multiplyRange(left, right, multiplier, node.rightChild);

        updateFromChildren(node);
    }


    /*------------------------------------------------------------
        RANGE QUERY
    ------------------------------------------------------------*/

    public int rangeQuery(int left, int right) {
        return rangeQuery(left, right, root);
    }

    private int rangeQuery(int left, int right, TreeNode node) {

        if (left > right) return 0;

        if (node.startIndex >= left && node.endIndex <= right)
            return (int) node.segmentSum;

        pushLazyToChildren(node);

        int result = 0;

        if (left <= node.midpoint)
            result = (result + rangeQuery(left, right, node.leftChild)) % MOD;

        if (right > node.midpoint)
            result = (result + rangeQuery(left, right, node.rightChild)) % MOD;

        return result;
    }


    /*------------------------------------------------------------
        HELPER FUNCTIONS
    ------------------------------------------------------------*/

    private void updateFromChildren(TreeNode node) {
        node.segmentSum =
                (node.leftChild.segmentSum +
                 node.rightChild.segmentSum) % MOD;
    }


    /**
     Push lazy operations down to children
     */
    private void pushLazyToChildren(TreeNode node) {

        if (node.leftChild == null)
            node.leftChild = new TreeNode(node.startIndex, node.midpoint);

        if (node.rightChild == null)
            node.rightChild = new TreeNode(node.midpoint + 1, node.endIndex);

        if (node.pendingAdd != 0 || node.pendingMultiply != 1) {

            TreeNode left = node.leftChild;
            TreeNode right = node.rightChild;

            /**
             Example transformation:

             Suppose operations were:
             multAll(2)
             addAll(3)

             Value transformation:
             x -> x*2 + 3
             */

            left.segmentSum =
                    (left.segmentSum * node.pendingMultiply +
                     (long)(left.endIndex - left.startIndex + 1) * node.pendingAdd)
                    % MOD;

            left.pendingAdd =
                    (left.pendingAdd * node.pendingMultiply + node.pendingAdd) % MOD;

            left.pendingMultiply =
                    (left.pendingMultiply * node.pendingMultiply) % MOD;


            right.segmentSum =
                    (right.segmentSum * node.pendingMultiply +
                     (long)(right.endIndex - right.startIndex + 1) * node.pendingAdd)
                    % MOD;

            right.pendingAdd =
                    (right.pendingAdd * node.pendingMultiply + node.pendingAdd) % MOD;

            right.pendingMultiply =
                    (right.pendingMultiply * node.pendingMultiply) % MOD;

            node.pendingAdd = 0;
            node.pendingMultiply = 1;
        }
    }
}


/**
 * Fancy Sequence API
 *
 * Example:
 *
 * append(2)
 * [2]
 *
 * addAll(3)
 * [5]
 *
 * append(7)
 * [5,7]
 *
 * multAll(2)
 * [10,14]
 */
class Fancy {

    private int size = 0;

    private SegmentTree tree = new SegmentTree();

    public Fancy() {}

    public void append(int value) {

        size++;

        // insert value at new position
        tree.addRange(size, size, value);
    }

    public void addAll(int increment) {

        tree.addRange(1, size, increment);
    }

    public void multAll(int multiplier) {

        tree.multiplyRange(1, size, multiplier);
    }

    public int getIndex(int index) {

        if (index >= size)
            return -1;

        return tree.rangeQuery(index + 1, index + 1);
    }
}