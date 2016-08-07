package LeetCode.DataTypes;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by warn on 7/8/2016.
 * mplement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
public class BSTIteratorStack {

    private Stack<TreeNode> treeNodeStack = new Stack<>();

    public BSTIteratorStack(TreeNode root) {
        if (root != null) {
            treeNodeStack.add(root);
            for (TreeNode pointer = root; pointer.left != null; pointer = pointer.left)
                treeNodeStack.add(pointer.left);
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !treeNodeStack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (hasNext()) {
            TreeNode pointer = treeNodeStack.pop();
            if (pointer.right != null) {
                treeNodeStack.add(pointer.right);
                for (TreeNode temp = pointer.right; temp.left != null; temp = temp.left) treeNodeStack.add(temp.left);
            }
            return pointer.val;
        } else throw new NoSuchElementException("No next element");
    }
}
