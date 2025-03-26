class Solution:
    def minOperations(self, grid: List[List[int]], x: int) -> int:
        # Step 1: Convert grid to 1D list
        flat_grid = [num for row in grid for num in row]

        # Step 2: Check feasibility (all numbers should have the same remainder when divided by x)
        remainder = flat_grid[0] % x
        for num in flat_grid:
            if num % x != remainder:
                return -1

        # Step 3: Find the median
        flat_grid.sort()
        median = flat_grid[len(flat_grid) // 2]

        # Step 4: Compute total operations to make all elements equal to the median
        operations = sum(abs(num - median) // x for num in flat_grid)

        return operations
