class Solution(object):
    def isPowerOfThree(self, n):
        """
        :type n: int
        :rtype: bool
        """
        # opposite of power of three is cube root
        # note that all powers of 3 are also divisible by 3
        # cube root is the power of 1/3
        # 1 is a power of three so any number that is a power of three can reduce to 1
        if n <= 0:
            return False
        while n % 3 == 0: 
            n //= 3
        return n == 1 # If the number reduces to 1 it is a power of three since 1 is a power of three


# solution id o(log n)

""" Another possible solution that is o(n)

if n <= 0:
      return False
`` n % x == 0, I chose 27 since that is a power of three
   if n % 27 == 0:
      return True
   else:
      return False

"""
