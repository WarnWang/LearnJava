package LeetCode.Algorithm.LinkedList;

import LeetCode.DataTypes.ListNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by warn on 14/6/2016.
 * store solutions to linked list puzzles
 */
public class Solution {

    /**
     * Given a linked list, determine if it has a cycle in it.
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

    public static void main(String args[]) {
        int length = 10;
        int[] linkedList = new int[length];
        for (int i = 0; i < length; i++) {
            linkedList[i] = i;
        }
        HashMap<Integer, ListNode> listMap = new HashMap<>();
        linkedList[9] = 2;
        ListNode head = new ListNode(linkedList[0]);
        listMap.put(linkedList[0], head);
        ListNode pointer = head;
        for (int i = 0; i < length; i++) {
            if (listMap.containsKey(linkedList[i])) {
                pointer.next = listMap.get(linkedList[i]);
                break;
            } else pointer.next = new ListNode(linkedList[i]);
        }

        Solution test = new Solution();
        System.out.println(test.detectCycleHashSet(head).val);
    }
}
