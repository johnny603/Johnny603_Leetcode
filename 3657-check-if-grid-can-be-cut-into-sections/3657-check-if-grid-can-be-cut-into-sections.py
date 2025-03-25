class Solution:
    def checkValidCuts(self, n: int, rectangles: List[List[int]]) -> bool:
        # Extract x-coordinates (start and end) of rectangles
        x = [(r[0], r[2]) for r in rectangles]
        # Extract y-coordinates (start and end) of rectangles
        y = [(r[1], r[3]) for r in rectangles]
        
        # Sort the x-coordinates and y-coordinates by starting position
        x.sort()
        y.sort()
        
        def count_non_overlapping(intervals):
            """
            This function counts the number of non-overlapping intervals.
            An interval is counted if it does not overlap with the previous one.
            """
            count = 0  # Number of non-overlapping sections
            prev_end = -1  # Keeps track of the end of the last counted interval
            
            for start, end in intervals:
                if prev_end <= start:  # If there's no overlap with the previous interval
                    count += 1  # Count this interval as a separate section
                prev_end = max(prev_end, end)  # Update the previous end position
            
            return count
        
        # We need at least three non-overlapping sections to form a valid cut
        return max(count_non_overlapping(x), count_non_overlapping(y)) >= 3
