class Solution:
    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        # Get the number of rows and columns
        ROWS, COLS = len(grid), len(grid[0])

        # Step 1: Sort queries and keep track of their original indices
        q = [(n, i) for i, n in enumerate(queries)]  # Store (query value, index)
        q.sort()  # Sorting queries in ascending order

        # Step 2: Initialize BFS with a min-heap (priority queue)
        min_heap = [(grid[0][0], 0, 0)]  # Start BFS from (0,0) with grid value
        visit = set([(0, 0)])  # Track visited cells
        res = [0] * len(queries)  # To store the results for each query
        points = 0  # Count the number of points collected

        # Step 3: Process each query in sorted order
        for limit, index in q:
            # Explore the grid while the smallest element is less than the current query limit
            while min_heap and min_heap[0][0] < limit:
                val, r, c = heappop(min_heap)  # Remove smallest element
                points += 1  # Increase count of collected points

                # Define possible moves: down, up, right, left
                neighbors = [[r+1, c], [r-1, c], [r, c+1], [r, c-1]]
                for nr, nc in neighbors:
                    # Check if the new position is within bounds and not visited
                    if 0 <= nr < ROWS and 0 <= nc < COLS and (nr, nc) not in visit:
                        heappush(min_heap, (grid[nr][nc], nr, nc))  # Push new cell into heap
                        visit.add((nr, nc))  # Mark as visited

            # Store the number of points collected for this query
            res[index] = points

        return res
