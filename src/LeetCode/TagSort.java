package LeetCode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by warn on 25/4/2016.
 * <p>
 * Save sort related puzzles
 */
public class TagSort {

    /**
     * Sort a linked list in O(n log n) time using constant space complexity.
     *
     * @param head a linked list
     * @return the sorted version of the linked list
     */
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (ListNode i = head; i != null; i = i.next){
            integerArrayList.add(head.val);
        }
        Collections.sort(integerArrayList);
        ListNode temp = head;
        for (Integer anIntegerArrayList : integerArrayList) {
            temp.val = anIntegerArrayList;
            temp = temp.next;
        }
        return head;
    }
}
