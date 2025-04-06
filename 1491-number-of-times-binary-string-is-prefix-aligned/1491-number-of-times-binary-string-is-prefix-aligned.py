class Solution(object):
    def numTimesAllBlue(self, flips):
        """
        :type flips: List[int]
        :rtype: int
        """
        right_most = 0
        moments = 0

        for i, bulb in enumerate(flips):
            right_most = max(right_most, bulb)
            if right_most == i + 1:
                moments += 1

        return moments
        