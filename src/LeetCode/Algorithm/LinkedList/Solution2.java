package LeetCode.Algorithm.LinkedList;

import LeetCode.DataTypes.ListNode;

/**
 * Created by warn on 22/7/2016.
 * Store answer to linked list questions
 */
public class Solution2 {
    /**
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
     * <p>
     * For example,
     * Given 1->2->3->3->4->4->5, return 1->2->5.
     * Given 1->1->1->2->3, return 2->3.
     *
     * @param head the root of a linked list
     * @return removed duplicated
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        head = deleteDuplicateNodes(head);

        for (ListNode pointer = head; pointer != null && pointer.next != null && pointer.next.next != null;
             pointer = pointer.next) {
            if (pointer.next.val == pointer.next.next.val) pointer.next = deleteDuplicateNodes(pointer.next);
        }
        return head;
    }

    private ListNode deleteDuplicateNodes(ListNode head) {
        while (head != null && head.next != null && head.val == head.next.val) {
            ListNode pointer = head;
            for (; pointer.next != null; pointer = pointer.next) {
                if (pointer.next.val != head.val) break;
            }
            head = pointer.next;
        }
        return head;
    }
}
