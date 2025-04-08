class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Merge the two lists into one sorted list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Traverse both lists and add nodes to the new list
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;  // Append list1 node to current list
                list1 = list1.next;    // Move list1 pointer to the next node
            } else {
                current.next = list2;  // Append list2 node to current list
                list2 = list2.next;    // Move list2 pointer to the next node
            }
            current = current.next; // Move current pointer to the last node
        }

        // Append any remaining nodes in list1 or list2
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        // The dummy node's next points to the head of the merged list
        return dummy.next;
    }

    // Merge Sort for sorting the linked list
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Find the middle of the list
        ListNode middle = findMiddle(head);
        ListNode nextToMiddle = middle.next;
        middle.next = null; // Split the list into two halves

        // Step 2: Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(nextToMiddle);

        // Step 3: Merge the two sorted halves
        return merge(left, right);
    }

    private ListNode findMiddle(ListNode head) {
        if (head == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Merge two sorted linked lists
        while (left != null && right != null) {
            if (left.val <= right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        // Append any remaining nodes from either list
        if (left != null) {
            current.next = left;
        } else {
            current.next = right;
        }

        return dummy.next;
    }
}
