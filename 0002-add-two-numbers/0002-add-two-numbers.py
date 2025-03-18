# Definition for singly-linked list.
# If next is none, that is the end if the linkedlist
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """

        # PHASE 1: Understanding
        # ---------------
        # Given non-negative integers
        # the variable types are singly-linked lists
        # The Input includes two singly likned lists
        # The output is one singly linked list
        # Input: l1 = [2,4,3], l2 = [5,6,4]
        # The digits are stored in reverse order, and each node contains a single digit
        # Output: [7,0,8]
        # I notice that 342 + 465 = 708
        # 2 + 5 = 7; 4 + 6 = 10; 3 + 4 = 7 plus a carried one is 8
        # Input: l1 = [1,2,3], l2 = [3,2,1]
        # Output: [4,4,4]
        # We need a carry variable to take in values greater than 9




        # PHASE 2: Matching
        # --------------
        # Let's try to match the input and output with some concrete examples:
        
        # Example 1:
        # Input: l1 = [2,4,3], l2 = [5,6,4]
        # Explanation:
        #   2 + 5 = 7
        #   4 + 6 = 10 (so we write down 0 and carry over 1)
        #   3 + 4 = 7, plus the carry 1, results in 8.
        # Thus, the final linked list is [7,0,8], which represents the sum 807.

        # Example 2:
        # Input: l1 = [1,2,3], l2 = [3,2,1]
        # Explanation:
        #   1 + 3 = 4
        #   2 + 2 = 4
        #   3 + 1 = 4
        # The final linked list is [4,4,4], representing the sum 444

        # IMPORTANT:
        # Notice that we need to handle cases where the sum of two digits results in a value greater than 9
        # When this happens, we need to keep track of a `carry` variable, which will be added to the next set of digits




        # Test my understanding
        print(l1.val + l2.val) # would print out 7

        # We would need a loop for both lists
        # While loop
        # Check for the condition: if next != None
        # Add the values of the lists at the same position
        # append a new linked list with the new values
        # convert to list

        # create a head of the linkedlist
        head = ListNode(0)
        current = head
        # initialize a carry
        carry = 0


        # Loop through both lists at once
        while l1 or l2 or carry:
            val1 = l1.val if l1 else 0
            val2 = l2.val if l2 else 0

            total = val1 + val2 + carry
            carry = total // 10
            # Create new list with the carry
            current.next = ListNode(total % 10)


            current = current.next
            if l1:
                l1 = l1.next
            if l2:
                l2 = l2.next

        return head.next