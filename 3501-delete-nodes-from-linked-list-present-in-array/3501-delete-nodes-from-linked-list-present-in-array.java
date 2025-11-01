class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        // Step 1: Store nums in a HashSet for O(1) lookups
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Step 2: Create a dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 3: Traverse the list
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            if (set.contains(curr.val)) {
                // Skip the node if it should be removed
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        // Step 4: Return the modified list
        return dummy.next;
    }
}
