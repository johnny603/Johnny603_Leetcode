/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }

 */
/*
class Solution {
    public int getDecimalValue(ListNode head) {
        StringBuilder binary = new StringBuilder();
        while (head != null) {
            binary.append(head.val);
            head = head.next;
        }
        return Integer.parseInt(binary.toString(), 2);
    }
}
*/

class Solution {
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            result = result * 2 + head.val; // Shift left and add current bit
            head = head.next;
        }
        return result;
    }
}

