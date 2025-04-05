class Solution:
    def subsetXORSum(self, nums: List[int]) -> int:
        def dfs(index, current_xor):
            if index == len(nums):
                return current_xor



            # ---------------------------------------------
            # Imagine the call stack like a binary tree:
            #
            # nums = [1, 3]
            #
            # dfs(0, 0)  # start
            # ├── include nums[0] = 1 → dfs(1, 0 ^ 1 = 1)
            # │   ├── include nums[1] = 3 → dfs(2, 1 ^ 3 = 2) → return 2
            # │   └── exclude nums[1]     → dfs(2, 1)         → return 1
            # └── exclude nums[0]        → dfs(1, 0)
            #     ├── include nums[1] = 3 → dfs(2, 0 ^ 3 = 3) → return 3
            #     └── exclude nums[1]     → dfs(2, 0)         → return 0
            #
            # Total: 2 + 1 + 3 + 0 = 6
            # ---------------------------------------------

            # Include current number
            include = dfs(index + 1, current_xor ^ nums[index])
            # Exclude current number
            exclude = dfs(index + 1, current_xor)
            return include + exclude

        return dfs(0, 0)
