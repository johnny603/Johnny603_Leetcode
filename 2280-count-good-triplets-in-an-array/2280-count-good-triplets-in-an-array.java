class Solution {
    class BIT {
        int[] tree;
        int size;

        public BIT(int n) {
            size = n + 2; // extra space to avoid out of bound
            tree = new int[size];
        }

        public void update(int i, int delta) {
            i++; // 1-based index
            while (i < size) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        public int query(int i) {
            i++; // 1-based index
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }

        public int queryRange(int i, int j) {
            return query(j) - query(i - 1);
        }
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos = new int[n];
        
        // Map nums2 values to their indices
        for (int i = 0; i < n; i++) {
            pos[nums2[i]] = i;
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = pos[nums1[i]];
        }

        BIT bit = new BIT(n);
        long[] left = new long[n];
        
        // left[i]: how many numbers < arr[i] before i
        for (int i = 0; i < n; i++) {
            left[i] = bit.query(arr[i] - 1);
            bit.update(arr[i], 1);
        }

        // Reset BIT for right calculation
        bit = new BIT(n);
        long res = 0;
        
        // right[i]: how many numbers > arr[i] after i
        for (int i = n - 1; i >= 0; i--) {
            long right = bit.query(n - 1) - bit.query(arr[i]);
            res += left[i] * right;
            bit.update(arr[i], 1);
        }

        return res;
    }
}
