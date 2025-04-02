class Solution(object):
    def maximumTripletValue(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        n = len(nums)
        max_value = 0  # Default to 0 if all values are negative

        for i in range(n - 2):  
            for j in range(i + 1, n - 1):  
                for k in range(j + 1, n):  
                    value = (nums[i] - nums[j]) * nums[k]
                    max_value = max(max_value, value)
        return max_value
        