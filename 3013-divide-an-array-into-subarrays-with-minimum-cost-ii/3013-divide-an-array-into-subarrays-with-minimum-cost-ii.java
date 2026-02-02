class Solution {

    // leftSet: holds the k-1 smallest elements in the current window
    // rightSet: holds the remaining elements in the window
    // We use TreeMap as a multiset (value -> frequency)
    private final TreeMap<Integer, Integer> leftSet = new TreeMap<>();
    private final TreeMap<Integer, Integer> rightSet = new TreeMap<>();

    // sumLeft = sum of elements currently stored in leftSet
    private long sumLeft;

    // leftSize = number of elements inside leftSet
    private int leftSize;

    public long minimumCost(int[] nums, int k, int dist) {

        // we already must include nums[0]
        // so we only need k-1 additional starting points
        k--;

        // sum always includes nums[0]
        sumLeft = nums[0];

        // initialize first sliding window [1 .. dist+1]
        // temporarily put everything into leftSet
        for (int i = 1; i <= dist + 1; i++) {
            sumLeft += nums[i];
            leftSet.merge(nums[i], 1, Integer::sum);
        }

        leftSize = dist + 1;

        // shrink leftSet until it holds exactly k elements
        // (i.e., keep only the k smallest)
        while (leftSize > k) {
            moveLargestLeftToRight();
        }

        long answer = sumLeft;

        // slide window
        for (int i = dist + 2; i < nums.length; i++) {

            // element leaving window
            int outgoing = nums[i - dist - 1];

            // remove outgoing from correct multiset
            if (leftSet.containsKey(outgoing)) {
                remove(leftSet, outgoing);
                sumLeft -= outgoing;
                leftSize--;
            } else {
                remove(rightSet, outgoing);
            }

            // element entering window
            int incoming = nums[i];

            // try to place into leftSet if it belongs among k smallest
            if (!leftSet.isEmpty() && incoming < leftSet.lastKey()) {
                leftSet.merge(incoming, 1, Integer::sum);
                sumLeft += incoming;
                leftSize++;
            } else {
                rightSet.merge(incoming, 1, Integer::sum);
            }

            // rebalance to maintain invariant:
            // leftSet must contain exactly k smallest elements
            while (leftSize < k) {
                moveSmallestRightToLeft();
            }
            while (leftSize > k) {
                moveLargestLeftToRight();
            }

            answer = Math.min(answer, sumLeft);
        }

        return answer;
    }

    // move largest element from leftSet → rightSet
    // this removes the worst (largest) candidate from chosen k
    private void moveLargestLeftToRight() {
        int x = leftSet.lastKey();

        remove(leftSet, x);
        sumLeft -= x;
        leftSize--;

        rightSet.merge(x, 1, Integer::sum);
    }

    // move smallest element from rightSet → leftSet
    // this adds the best available candidate
    private void moveSmallestRightToLeft() {
        int x = rightSet.firstKey();

        remove(rightSet, x);
        leftSet.merge(x, 1, Integer::sum);

        sumLeft += x;
        leftSize++;
    }

    // helper to remove one occurrence from a multiset
    private void remove(TreeMap<Integer, Integer> map, int x) {
        if (map.merge(x, -1, Integer::sum) == 0) {
            map.remove(x);
        }
    }
}
