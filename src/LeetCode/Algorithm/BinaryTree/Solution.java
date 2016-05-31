package LeetCode.Algorithm.BinaryTree;

import LeetCode.DataTypes.TreeNode;

/**
 * Created by warn on 31/5/2016.
 * Store solution to complete binary tree
 */
public class Solution {

    /**
     * Given a complete binary tree, count the number of nodes.
     * <p>
     * Definition of a complete binary tree from Wikipedia:
     * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last
     * level are as far left as possible. It can have between 1 and 2^h nodes inclusive at the last level h.
     *
     * @param root a complete binary tree
     * @return the nodes number of the binary tree
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode leftKid = root.left, rightKid = root.right;
        int leftDepth = 0, rightDepth = 0;
        while (leftKid != null) {
            leftDepth++;
            leftKid = leftKid.left;
        }
        while (rightKid != null) {
            rightDepth++;
            rightKid = rightKid.right;
        }
        if (rightDepth == leftDepth) return (2 << leftDepth) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
