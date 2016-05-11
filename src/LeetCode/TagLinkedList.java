package LeetCode;

import java.util.HashMap;

/**
 * Created by warn on 22/3/2016.
 * Store problems with tag linked list
 */
public class TagLinkedList {
    /**
     * Given a singly linked list, determine if it is a palindrome.
     *
     * @param head A singly linked list
     * @return whether this is palindrome or not
     */
    public boolean isPalindrome(ListNode head) {
        int nNode = 0;
        ListNode p = head;
        while (p != null) {
            nNode++;
            p = p.next;
        }
        int firstHalfIndex, secondHalfIndex;
        if (nNode <= 1) return true;
        else if ((nNode & 1) == 0) {
            firstHalfIndex = nNode / 2 - 1;
            secondHalfIndex = nNode / 2;
        } else {
            firstHalfIndex = nNode / 2 - 1;
            secondHalfIndex = nNode / 2 + 1;
        }

        ListNode secondHead = null, firstHead = head, firstTailNext = head.next;
        for (int i = 1; i < nNode; i++) {
            if (i <= firstHalfIndex) {
                ListNode temp;
                head.next = firstTailNext.next;
                firstTailNext.next = firstHead;
                temp = head.next;
                firstHead = firstTailNext;
                firstTailNext = temp;
            } else if (i >= secondHalfIndex) {
                if (secondHead == null) secondHead = firstTailNext;
                if (secondHead.val != firstHead.val) return false;
                secondHead = secondHead.next;
                firstHead = firstHead.next;
            } else firstTailNext = firstTailNext.next;
        }
        return true;
    }

    /**
     * Reverse a singly linked list.
     *
     * @param head a single linked list
     * @return reversed linked list
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode tail = head;
        ListNode tailNext = tail.next;
        while (tailNext != null) {
            tail.next = tailNext.next;
            tailNext.next = head;
            head = tailNext;
            tailNext = tail.next;
        }
        return head;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode subTail, subTailNext, subTailHead;
        ListNode pointer = null;
        if (m == 1) {
            subTailHead = head;
        } else {
            pointer = head;
            for (int i = 2; i < m; i++) {
                pointer = pointer.next;
            }
            subTailHead = pointer.next;
        }
        subTail = subTailHead;
        subTailNext = subTailHead.next;
        for (; m < n && subTailNext != null; m++) {
            subTail.next = subTailNext.next;
            subTailNext.next = subTailHead;
            subTailHead = subTailNext;
            subTailNext = subTail.next;
        }
        if (pointer == null) head = subTailHead;
        else pointer.next = subTailHead;
        return head;
    }

    /**
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than
     * or equal to x.
     * You should preserve the original relative order of the nodes in each of the two partitions.
     *
     * @param head a linked list
     * @param x    a value x
     * @return nodes less than x come before nodes greater than or equal to x
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode i, tail;
        if (head.val >= x) {
            ListNode findLower = head;
            while (findLower.next != null) {
                if (findLower.next.val < x) {
                    ListNode temp = findLower.next;
                    findLower.next = findLower.next.next;
                    temp.next = head;
                    head = temp;
                    break;
                }
                findLower = findLower.next;
            }
            if (findLower.next == null) return head;
            else {
                i = head.next;
                tail = head;
            }
        } else {
            ListNode j;
            for (j = head; j.next != null && j.next.val < x; j = j.next) ;
            if (j.next == null) return head;
            else {
                i = j.next;
                tail = j;
            }
        }
        for (ListNode frontier = tail; i.next != null; ) {
            if (i.next.val < x) {
                ListNode temp = i.next;
                i.next = i.next.next;
                temp.next = frontier.next;
                frontier.next = temp;
                frontier = frontier.next;
            } else i = i.next;
        }
        return head;
    }

    /**
     * Return a deep copy of the random list node.
     *
     * @param head that each node contains an additional random pointer which could point to any node in the list or
     *             null
     * @return the deep copy of the given node
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        HashMap<RandomListNode, RandomListNode> existsNode = new HashMap<>();
        for (RandomListNode pointer = head; pointer != null; pointer = pointer.next) {
            existsNode.put(pointer, new RandomListNode(pointer.label));
        }

        for (RandomListNode pointer = head; pointer != null; pointer = pointer.next){
            if (pointer.next != null){
                existsNode.get(pointer).next = existsNode.get(pointer.next);
            }

            if (pointer.random != null) {
                existsNode.get(pointer).random = existsNode.get(pointer.random);
            }
        }
        return existsNode.get(head);
    }
}
