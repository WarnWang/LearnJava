package LeetCode.Algorithm.TwoPointer;

import LeetCode.DataTypes.ListNode;

/**
 * Created by warn on 29/9/2016.
 * Store problem with tag two pointer
 */
public class Solution {
    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * <p>
     * For example,
     * "A man, a plan, a canal: Panama" is a palindrome.
     * "race a car" is not a palindrome.
     *
     * @param s a string
     * @return whether this string is palindrome
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        for (int i = 0, j = s.length() - 1; i < j; ) {
            char cI = s.charAt(i), cJ = s.charAt(j);
            while (!Character.isAlphabetic(cI) || !Character.isDigit(cI)) {
                i++;
                if (i >= j) return true;
                cI = s.charAt(i);
            }

            while (!Character.isAlphabetic(cJ) || !Character.isDigit(cJ)) {
                j--;
                if (i >= j) return true;
                cJ = s.charAt(j);
            }
            if (Character.toLowerCase(cI) != Character.toLowerCase(cJ)) return false;
            else {
                i++;
                j--;
            }
        }
        return true;
    }

    /**
     * Given a linked list, remove the nth node from the end of list and return its head.
     * <p>
     * For example,
     * <p>
     * Given linked list: 1->2->3->4->5, and n = 2.
     * <p>
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     * Given n will always be valid.
     * Try to do this in one pass.
     *
     * @param head a linked list
     * @param n the node that need to be removed
     * @return the removed linked list
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        ListNode tail = head;
        for (int i = 0; i < n; i++) {
            tail = tail.next;
        }
        if (tail == null) return head.next;
        else {
            while (tail.next != null) {
                tail = tail.next;
                node = node.next;
            }

            node.next = node.next.next;
            return head;
        }
    }
}
