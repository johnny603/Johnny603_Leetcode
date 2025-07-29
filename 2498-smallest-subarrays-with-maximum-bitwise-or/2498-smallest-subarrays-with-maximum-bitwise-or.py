class Solution:
    def smallestSubarrays(self, nums):
        n = len(nums)
        res = [1] * n
        bit_positions = [-1] * 32  # Tracks latest index where each bit appears

        for i in range(n - 1, -1, -1):
            for b in range(32):
                if (nums[i] >> b) & 1:
                    bit_positions[b] = i

            farthest = i
            for b in range(32):
                if bit_positions[b] != -1:
                    farthest = max(farthest, bit_positions[b])
            
            res[i] = farthest - i + 1
        
        return res
