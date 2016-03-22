package LeetCode;

/**
 * Created by warn on 22/3/2016.
 * Store problems with tag linked list
 */
public class LinkedList {
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
}
