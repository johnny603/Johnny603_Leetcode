class Solution:
    def minimumIndex(self, nums: List[int]) -> int:
        # Step 1: Find the dominant element using Boyer-Moore Majority Vote Algorithm
        candidate = None 
        count = 0
        for num in nums:
            if count == 0:
                candidate = num
            count += (1 if num == candidate else -1)
        
        # Step 2: Confirm that candidate is actually the dominant element
        total_count = nums.count(candidate)
        if total_count * 2 <= len(nums):  # If it doesn't appear more than half
            return -1
        
        # Step 3: Find the minimum valid split
        left_count = 0
        for i in range(len(nums) - 1):  # Avoid splitting at the last index
            if nums[i] == candidate:
                left_count += 1
            
            left_size = i + 1
            right_size = len(nums) - left_size
            right_count = total_count - left_count
            
            if left_count * 2 > left_size and right_count * 2 > right_size:
                return i
        
        return -1