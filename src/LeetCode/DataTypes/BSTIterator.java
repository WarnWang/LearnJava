package LeetCode.DataTypes;

import java.util.NoSuchElementException;

/**
 * Created by warn on 7/8/2016.
 * mplement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * This version is based on Morris method
 */
public class BSTIterator {

    TreeNode pointer = null;

    public BSTIterator(TreeNode root) {
        pointer = root;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return pointer != null;
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (hasNext()) {
            int x = 0;
            while (pointer != null) {
                if (pointer.left == null) {
                    x = pointer.val;
                    pointer = pointer.right;
                    break;
                } else {
                    TreeNode temp = pointer.left;
                    while (temp.right != null && temp.right != pointer) {
                        temp = temp.right;
                    }
                    if (temp.right == null) {
                        temp.right = pointer;
                        pointer = pointer.left;
                    } else {
                        x = pointer.val;
                        pointer = pointer.right;
                        temp.right = null;
                        break;
                    }
                }
            }
            return x;
        } else throw new NoSuchElementException("No next element");
    }
}
