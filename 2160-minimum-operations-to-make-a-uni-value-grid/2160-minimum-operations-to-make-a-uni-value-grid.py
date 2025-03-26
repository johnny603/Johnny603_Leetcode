from typing import List

class Solution:
    def minOperations(self, grid: List[List[int]], x: int) -> int:
        # Step 1: Convert the 2D grid into a 1D list
        # We iterate through each row and each element in the row to flatten the grid.
        flat_grid = [num for row in grid for num in row]

        # Step 2: Check feasibility
        # We must ensure all elements have the same remainder when divided by x.
        remainder = flat_grid[0] % x  # Get remainder of the first element
        for num in flat_grid:
            if num % x != remainder:
                return -1  # If any number has a different remainder, return -1 (not possible)

        # Step 3: Find the median
        # Sorting the list allows us to determine the median efficiently.
        flat_grid.sort()
        median = flat_grid[len(flat_grid) // 2]  # Median minimizes total absolute differences

        # Step 4: Compute total operations needed
        # The minimum operations required is the sum of moves needed to align all elements to the median.
        operations = sum(abs(num - median) // x for num in flat_grid)

        # Return the total number of operations required
        return operations
