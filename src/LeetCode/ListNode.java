package LeetCode;

/**
 * Created by warn on 19/2/2016.
 */


public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    public static void main(String[] args) {
        // put your codes here
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = null;
        ListNode tempB = null;
        ListNode temp;
        while (headA != null) {
            temp = new ListNode(headA.val);
            temp.next = tempA;
            tempA = temp;
            headA = headA.next;
        }

        while (headB != null) {
            temp = new ListNode(headB.val);
            temp.next = tempB;
            tempB = temp;
            headB = headB.next;
        }
        if (tempA == null || tempB == null) return null;
        else if (tempA.val != tempB.val) return null;

        do {
            temp = tempA;
            tempA = tempA.next;
            tempB = tempB.next;
        } while (tempA != null && tempB != null && tempA.val == tempB.val);

        return temp;
    }
}