package LeetCode.Algorithm.LinkedList;

import LeetCode.DataTypes.ListNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by warn on 14/6/2016.
 * store solutions to linked list puzzles
 */
public class Solution {

    public static void main(String args[]) {
        int length = 10;
        int[] linkedList = new int[length];
        for (int i = 0; i < length; i++) {
            linkedList[i] = i;
        }
//        HashMap<Integer, ListNode> listMap = new HashMap<>();
        linkedList[9] = 2;
        ListNode head = new ListNode(linkedList[0]);
//        listMap.put(linkedList[0], head);
        ListNode pointer = head;
        for (int i = 1; i < length; i++) {
//            if (listMap.containsKey(linkedList[i])) {
//                pointer.next = listMap.get(linkedList[i]);
//                break;
//            } else
            pointer.next = new ListNode(linkedList[i]);
            pointer = pointer.next;
        }

        Solution test = new Solution();
        System.out.println(test.reverseKGroup(head, 2).val);
    }

    /**
     * Given a linked list, determine if it has a cycle in it.
     *
     * @param head a linked list
     * @return contains cycle or not
     */
    public boolean hasCycleHashSet(ListNode head) {
        HashSet<ListNode> nodeHashSet = new HashSet<>();
        for (ListNode pointer = head; pointer != null; pointer = pointer.next) {
            if (nodeHashSet.contains(pointer)) return true;
            nodeHashSet.add(pointer);
        }
        return false;
    }

    public boolean hasCycleTwoPointer(ListNode head) {
        if (head != null) {
            ListNode fast = head.next;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                if (fast == slow) return true;
                fast = fast.next.next;
                slow = slow.next;
            }
        }
        return false;
    }

    /**
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     *
     * @param head a linked list
     * @return the start points of the cycle begins
     */
    public ListNode detectCycleHashSet(ListNode head) {
        HashSet<ListNode> nodeHashSet = new HashSet<>();
        for (ListNode pointer = head; pointer != null; pointer = pointer.next) {
            if (nodeHashSet.contains(pointer)) return pointer;
            nodeHashSet.add(pointer);
        }
        return null;
    }

    // details can be found here http://www.cnblogs.com/snake-hand/p/3148328.html
    public ListNode detectCycleTwoPointer(ListNode head) {
        if (head != null) {
            ListNode slow = head, fast = head.next;
            while (fast != slow) {
                if (fast == null || fast.next == null) return null;
                slow = slow.next;
                fast = fast.next.next;
            }

            while (head != slow.next) {
                head = head.next;
                slow = slow.next;
            }
            return head;

        }
        return null;
    }

    /**
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     * <p>
     * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     * <p>
     * You may not alter the values in the nodes, only nodes itself may be changed.
     * <p>
     * Only constant memory is allowed.
     * <p>
     * For example,
     * Given this linked list: 1->2->3->4->5
     * <p>
     * For k = 2, you should return: 2->1->4->3->5
     * <p>
     * For k = 3, you should return: 3->2->1->4->5
     *
     * @param head a linked list
     * @param k the group size
     * @return reverse k group linked list
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) return head;
        if (head == null || head.next == null) return head;
        int groupIndex = 0;
        ListNode newList = null, newListTail = null, currentHead=head;
        for (ListNode pointer = head; pointer != null; pointer = pointer.next) {
            groupIndex++;
            if (groupIndex == k) {
                ListNode[] newTail = reverseGroup(currentHead, pointer);
                if (newList == null) {
                    newList = newTail[0];
                    newListTail = newTail[1];
                } else {
                    newListTail.next = newTail[0];
                    newListTail = newTail[1];
                }
                currentHead = pointer.next;
                groupIndex = 0;
            }
        }
        if (groupIndex != 0) {
            if (newList == null) return head;
            else newListTail.next = currentHead;
        }
        return newList;
    }

    private ListNode[] reverseGroup(ListNode head, ListNode tail) {
        ListNode newHead = new ListNode(head.val), pointer = head.next, newTail = newHead;
        for (; pointer != tail; pointer = pointer.next) {
            ListNode temp = new ListNode(pointer.val);
            temp.next = newHead;
            newHead = temp;
        }
        ListNode temp = new ListNode(tail.val);
        temp.next = newHead;
        return new ListNode[] {newHead, newTail};
    }
}
