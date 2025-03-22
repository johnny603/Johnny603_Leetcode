class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: float
        """
        
        # Merge the two arrays
        nums1.extend(nums2)
        
        # Sort the merged array
        nums1.sort()

        # Length of the merged array
        n = len(nums1)

        # If the length is even, return the average of the two middle elements
        if n % 2 == 0:
            mid1 = nums1[n // 2 - 1]
            mid2 = nums1[n // 2]
            return (mid1 + mid2) / 2.0
        else:
            # If the length is odd, return the middle element
            return float(nums1[n // 2])


# STEPS
# Merge lists using extend
# If the array is odd, the midpoint is the median
# If the array is even, the midpoint is the two midpoints / 2

    
        