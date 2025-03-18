class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        lastNonZeroFoundAt = 0
    
        # Move non-zero elements to the front
        for i in range(len(nums)):
            if nums[i] != 0:
                # Swap the current element with the one at lastNonZeroFoundAt
                nums[lastNonZeroFoundAt], nums[i] = nums[i], nums[lastNonZeroFoundAt]
                lastNonZeroFoundAt += 1
