package LeetCode.Algorithm.BinaryTree;

import LeetCode.DataTypes.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    /**
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * <p>
     * For example:
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [1,2,3].
     *
     * @param root a binary tree
     * @return the pre-order traversal
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> preOrder = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> exploreStack = new Stack<>();
            exploreStack.add(root);
            while (!exploreStack.isEmpty()) {
                TreeNode frontier = exploreStack.pop();
                preOrder.add(frontier.val);

                if (frontier.right != null) exploreStack.add(frontier.right);
                if (frontier.left != null) exploreStack.add(frontier.left);
            }
        }
        return preOrder;
    }
}
