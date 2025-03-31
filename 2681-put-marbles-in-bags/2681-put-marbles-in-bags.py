class Solution(object):
    def putMarbles(self, weights, k):
        """
        :type weights: List[int]
        :type k: int
        :rtype: int
        """
        n = len(weights)
        
        # If k == 1, no partitioning is needed, the difference is 0
        if k == 1:
            return 0
        
        # Calculate the sum of adjacent pairs
        diffs = [weights[i] + weights[i + 1] for i in range(n - 1)]
        
        # Sort the differences in descending order to easily get the largest (k-1) differences
        diffs.sort(reverse=True)
        
        # The answer is the sum of the largest (k-1) differences minus the sum of the smallest (k-1) differences
        return sum(diffs[:k-1]) - sum(diffs[-(k-1):])