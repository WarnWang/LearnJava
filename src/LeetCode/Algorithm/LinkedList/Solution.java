package LeetCode.Algorithm.LinkedList;

import LeetCode.DataTypes.ListNode;
import net.datastructures.List;

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


    public ListNode detectCycleTwoPointer(ListNode head) {
        if (head != null) {
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {

            }
        }
        return null;
    }
}
