class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Create a new array to hold the merged result
        int[] mergedArray = new int[nums1.length + nums2.length];

        // Copy elements from both arrays into the merged array
        System.arraycopy(nums1, 0, mergedArray, 0, nums1.length);
        System.arraycopy(nums2, 0, mergedArray, nums1.length, nums2.length);

        Arrays.sort(mergedArray);
        int n = mergedArray.length;

        if (mergedArray.length % 2 != 0) {
            return mergedArray[n / 2];
        } else {
            double mid1 = mergedArray[n / 2];
            double mid2 = mergedArray[n / 2 - 1];
            return (mid1 + mid2) / 2;
        }

        

    }
}

// Merge arrays using arraycopy() method
// If the array is odd, the midpoint is the median
// If the array is even, the midpoint is the two midpoints / 2