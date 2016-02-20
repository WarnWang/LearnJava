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
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(0);
        ListNode tempA = a;
        ListNode tempB = b;
        b.next = a;
//        b = b.next;
        for (int i = 0; i < 2; i++) {
            ListNode temp = new ListNode(a.val + 2);
            a.next = temp;
            a = temp;
//            temp = new ListNode(b.val + 2);
//            b.next = temp;
//            b = temp;
        }

        System.out.println(a.getIntersectionNode3(tempA, tempB).val);
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return b;
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

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode tempA = headA;
        ListNode tempB = headB;

        while (tempA != null || tempB != null) {
            if (tempA == null) headB = headB.next;
            else tempA = tempA.next;
            if (tempB == null) headA = headA.next;
            else tempB = tempB.next;
        }

        while (headA != headB && headA != null && headB != null) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }
}