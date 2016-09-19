package LeetCode.Algorithm.DepthFirstSearch;

import LeetCode.DataTypes.ListNode;
import LeetCode.DataTypes.TreeNode;

import java.util.ArrayList;

/**
 * Created by warn on 5/9/2016.
 * Store solution to complete binary tree
 */
public class Solution2 {
    /**
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * @param head a singly linked list where elements are sorted in ascending order,
     * @return a height balanced BST
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ArrayList<Integer> linkedList = new ArrayList<>();
        for (ListNode pointer = head; pointer != null; pointer = pointer.next) linkedList.add(pointer.val);
        return buildBST(linkedList, 0, linkedList.size());
    }

    private TreeNode buildBST(ArrayList<Integer> linkedList, int start, int end) {
        if (start >= end) return null;
        int middle = (start + end) / 2;
        TreeNode root = new TreeNode(linkedList.get(middle));
        root.left = buildBST(linkedList, start, middle);
        root.right = buildBST(linkedList, middle + 1, end);
        return root;
    }
}
