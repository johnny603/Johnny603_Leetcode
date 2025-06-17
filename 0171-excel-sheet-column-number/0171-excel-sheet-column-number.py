class Solution(object):
    def titleToNumber(self, columnTitle):
        """
        :type columnTitle: str
        :rtype: int
        """
        x = 0
        for c in columnTitle:
            value = ord(c) - ord('A') + 1
            x = x * 26 + value
        return x

        