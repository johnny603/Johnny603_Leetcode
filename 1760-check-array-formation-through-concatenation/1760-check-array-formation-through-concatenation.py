class Solution(object):
    def canFormArray(self, arr, pieces):
        """
        :type arr: List[int]
        :type pieces: List[List[int]]
        :rtype: bool
        """

        piece_map = {p[0]: p for p in pieces}  # Create a hash table for quick lookup
    
        i = 0
        while i < len(arr):
            if arr[i] not in piece_map:  # If no piece starts with arr[i], return False
                return False
        
            piece = piece_map[arr[i]]  # Get the corresponding piece
            if arr[i:i+len(piece)] != piece:  # Check if subarray matches
                return False
        
            i += len(piece)  #  Move index forward by length of matched piece (inside loop)
    
        return True
