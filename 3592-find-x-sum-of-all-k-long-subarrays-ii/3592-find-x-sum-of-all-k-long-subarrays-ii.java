class Solution {
    // TreeSet l: stores the top x frequent (freq, value) pairs
    // Sorted by frequency ASC, then value ASC — so the smallest/top element is first()
    private TreeSet<int[]> l = new TreeSet<>((a, b) -> 
        a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]
    );

    // TreeSet r: stores the rest of the elements (not in top x)
    // Uses the same comparator for consistent ordering
    private TreeSet<int[]> r = new TreeSet<>(l.comparator());

    // Frequency map of each number in the current window
    private Map<Integer, Integer> cnt = new HashMap<>();

    // Running sum of (freq * value) for all elements in l (the top x)
    private long s;

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] ans = new long[n - k + 1];

        // Iterate through each element (expand sliding window)
        for (int i = 0; i < n; ++i) {
            int v = nums[i];

            // Step 1: Remove the old frequency record of v before incrementing it
            remove(v);

            // Step 2: Update its frequency in the map
            cnt.merge(v, 1, Integer::sum);

            // Step 3: Add it back with the new frequency
            add(v);

            // Step 4: Check if we have formed a window of size k yet
            int j = i - k + 1;
            if (j < 0) continue;

            // Step 5: Balance sets — make sure l contains *exactly x* most frequent elements

            // Move the most frequent elements from r → l until l has x elements
            while (!r.isEmpty() && l.size() < x) {
                var p = r.pollLast(); // get the largest from r
                s += 1L * p[0] * p[1];
                l.add(p);
            }

            // If l has more than x elements, move the least frequent back to r
            while (l.size() > x) {
                var p = l.pollFirst(); // smallest in l
                s -= 1L * p[0] * p[1];
                r.add(p);
            }

            // Step 6: Record the current x-sum
            ans[j] = s;

            // Step 7: Slide the window — remove the outgoing element nums[j]
            remove(nums[j]);
            cnt.merge(nums[j], -1, Integer::sum);

            // If frequency becomes 0, it will be skipped in future adds
            add(nums[j]);
        }
        return ans;
    }

    // Removes a number’s (freq, val) pair from the sets before updating its freq
    private void remove(int v) {
        if (!cnt.containsKey(v)) return;

        var p = new int[] {cnt.get(v), v};

        // If currently in l, remove it and update running sum
        if (l.contains(p)) {
            l.remove(p);
            s -= 1L * p[0] * p[1];
        } else {
            // Otherwise it’s in r
            r.remove(p);
        }
    }

    // Adds a number’s (freq, val) pair to the appropriate set after updating its freq
    private void add(int v) {
        if (!cnt.containsKey(v)) return;

        var p = new int[] {cnt.get(v), v};

        // If it’s more frequent than the smallest in l → belongs to l
        if (!l.isEmpty() && l.comparator().compare(l.first(), p) < 0) {
            l.add(p);
            s += 1L * p[0] * p[1];
        } else {
            // Otherwise stays in r
            r.add(p);
        }
    }
}
