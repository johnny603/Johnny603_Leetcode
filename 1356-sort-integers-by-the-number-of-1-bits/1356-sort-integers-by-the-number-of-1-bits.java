class Solution {
    public int[] sortByBits(int[] arr) {

        // Sort the array using a custom comparator
        // We convert to Integer[] because Arrays.sort with comparator
        // does not work directly on primitive int[]
        Integer[] boxed = new Integer[arr.length];

        // Copy values into boxed array
        for (int i = 0; i < arr.length; i++) {
            boxed[i] = arr[i];
        }

        Arrays.sort(boxed, (a, b) -> {
            // Count number of 1-bits in each number
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);

            // Primary sort: by number of set bits
            if (countA != countB) {
                return countA - countB;
            }

            // Secondary sort: by numeric value
            return a - b;
        });

        // Convert back to primitive int[]
        int[] sorted = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            sorted[i] = boxed[i];
        }

        return sorted;
    }
}