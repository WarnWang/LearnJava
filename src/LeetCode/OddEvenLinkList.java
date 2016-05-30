package LeetCode;

import LeetCode.DataTypes.ListNode;

/**
 * Created by warn on 23/1/2016.
 */


public class OddEvenLinkList {
    public static void main(String[] args) {
        // put your codes here
        ListNode testData = new ListNode(1);
        ListNode head = testData;
        ListNode temp;
        for (int i = 1; i < 2; i++) {
            temp = new ListNode(i);
            testData.next = temp;
            testData = testData.next;
        }

        OddEvenLinkList test = new OddEvenLinkList();
        test.oddEvenList(head);
    }


    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result;
        ListNode firstPart = head;
        ListNode secondPart = head.next;
        ListNode secondPartHead = secondPart;
        firstPart.next = secondPart;
        result = firstPart;
        while (head.next.next != null) {
            head = head.next.next;
            firstPart.next = head;
            secondPart.next = head.next;
            firstPart = firstPart.next;
            secondPart = secondPart.next;
            if (head.next == null) break;
        }
        firstPart.next = secondPartHead;
        return result;
    }
}