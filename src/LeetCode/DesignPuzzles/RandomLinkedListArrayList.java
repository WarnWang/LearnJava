package LeetCode.DesignPuzzles;

import LeetCode.DataTypes.ListNode;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by warn on 12/8/2016.
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same
 * probability of being chosen.
 * <p>
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently
 * without using extra space?
 * <p>
 * Example:
 * <p>
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * <p>
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 * 143 ms
 */
public class RandomLinkedListArrayList {

    private ArrayList<Integer> nodeList;
    private Random random;

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public RandomLinkedListArrayList(ListNode head) {
        nodeList = new ArrayList<>();
        for (ListNode i = head; i != null; i = i.next) {
            nodeList.add(i.val);
        }
        random = new Random();
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        return nodeList.get(random.nextInt(nodeList.size()));
    }
}
