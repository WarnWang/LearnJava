package LeetCode;

/**
 * Created by warn on 11/5/2016.
 *
 * Random List node class
 * A linked list is given such that each node contains an additional random pointer which could point to any node in
 * the list or null.
 */
public class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
}
