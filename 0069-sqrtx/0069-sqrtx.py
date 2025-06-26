class Solution(object):
    def mySqrt(self, x):
        """
        :type x: int
        :rtype: int
        """
        if x < 2:
            return x

        low = 1
        high = x // 2  # Optimization

        while low <= high:
            mid = low + (high - low) // 2
            square = mid * mid

            if square == x:
                return mid
            elif square < x:
                low = mid + 1
            else:
                high = mid - 1  # Corrected from your Java version

        return high  # high is the integer part of sqrt(x)
