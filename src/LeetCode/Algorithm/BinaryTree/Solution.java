package LeetCode.Algorithm.BinaryTree;

import LeetCode.DataTypes.TreeNode;

import java.util.*;

/**
 * Created by warn on 31/5/2016.
 * Store solution to complete binary tree
 */
public class Solution {

    private boolean recovered = false;
    private TreeNode miniMum;
    private TreeNode maxiMum;
    private boolean isValid;

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
     * Two elements of a binary search tree (BST) are swapped by mistake.
     * <p>
     * Recover the tree without changing its structure.
     *
     * @param root A binary search tree (BST) with some mistake
     */
    public void recoverTree(TreeNode root) {
        if (root == null || recovered) return;
        miniMum = null;
        maxiMum = null;
        findGreatest(root.left, false);
        findGreatest(root.right, true);
        if (miniMum != null && miniMum.val < root.val && maxiMum != null && maxiMum.val > root.val) {
            int tmp = miniMum.val;
            miniMum.val = maxiMum.val;
            maxiMum.val = tmp;
            recovered = true;
        } else if (miniMum != null && miniMum.val < root.val) {
            int tmp = miniMum.val;
            miniMum.val = root.val;
            root.val = tmp;
            recovered = true;
        } else if (maxiMum != null && maxiMum.val > root.val) {
            int tmp = maxiMum.val;
            maxiMum.val = root.val;
            root.val = tmp;
            recovered = true;
        } else {
            if (miniMum != null) System.out.println(miniMum.val);
            if (maxiMum != null) System.out.println(maxiMum.val);
            recoverTree(root.left);
            recoverTree(root.right);
        }
    }

    private void findGreatest(TreeNode root, boolean isMinimum) {
        if (root == null) return;
        if (isMinimum) {
            if (miniMum == null || miniMum.val > root.val) miniMum = root;
            System.out.println(root.val);
        } else {
            if (maxiMum == null || maxiMum.val < root.val) maxiMum = root;
        }
        findGreatest(root.left, isMinimum);
        findGreatest(root.right, isMinimum);
    }

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * <p>
     * Assume a BST is defined as follows:
     * <p>
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * Example 1:
     * 2
     * / \
     * 1   3
     * Binary tree [2,1,3], return true.
     * Example 2:
     * 1
     * / \
     * 2   3
     * Binary tree [1,2,3], return false.
     *
     * @param root a root binary tree
     * @return whether it is a valid BST
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        isValid = true;
        if (root.left == null && root.right == null) return true;
        else if (root.left == null) {
            int[] rightRange = getBSTNode(root.right);
            if (rightRange[0] <= root.val) isValid = false;
        } else if (root.right == null) {
            int[] leftRange = getBSTNode(root.left);
            if (leftRange[1] >= root.val) isValid = false;
        } else {
            int[] rightRange = getBSTNode(root.right);
            int[] leftRange = getBSTNode(root.left);
            System.out.println(Arrays.toString(rightRange));
            System.out.println(Arrays.toString(leftRange));
            if (rightRange[0] <= root.val || leftRange[1] >= root.val) isValid = false;
        }
        return isValid;
    }

    private int[] getBSTNode(TreeNode root) {
        if (!isValid) return new int[]{0, 0};
        if (root.left != null && root.right != null) {
            int[] leftRange = getBSTNode(root.left);
            int[] rightRange = getBSTNode(root.right);
            if (leftRange[1] >= root.val || rightRange[0] <= root.val) isValid = false;
            return new int[]{leftRange[0], rightRange[1]};
        } else if (root.left != null) {
            int[] leftRange = getBSTNode(root.left);
            if (leftRange[1] >= root.val) isValid = false;
            return new int[]{leftRange[0], root.val};
        } else if (root.right != null) {
            int[] rightRange = getBSTNode(root.right);
            if (rightRange[0] <= root.val) isValid = false;
            return new int[]{root.val, rightRange[1]};
        } else return new int[]{root.val, root.val};
    }

    /**
     * Given a binary tree, return the postorder traversal of its nodes' values.
     * <p>
     * For example:
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [3,2,1].
     *
     * @param root a binary tree
     * @return the postorder of binary tree
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> postOrder = new LinkedList<>();
        if (root == null) return postOrder;
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.add(root);
        while (!treeNodeStack.isEmpty()) {
            TreeNode frontier = treeNodeStack.pop();
            postOrder.addFirst(frontier.val);
            if (frontier.left != null) treeNodeStack.add(frontier.left);
            if (frontier.right != null) treeNodeStack.add(frontier.right);
        }
        return postOrder;
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