package LeetCode.Algorithm.BreadthFirstSearch;

import LeetCode.DataTypes.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by warn on 9/6/2016.
 * Solution to breadth first search puzzles
 */
public class Solution {
    /**
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
     * <p>
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its bottom-up level order traversal as:
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     *
     * @param root a binary tree
     * @return bottom-up level order traversal of the binary tree
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> traversal = new LinkedList<>();
        if (root == null) return traversal;
        ArrayList<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root);
        while (!levelNodes.isEmpty()) {
            ArrayList<TreeNode> nextLevelNodes = new ArrayList<>();
            ArrayList<Integer> currentLevel = new ArrayList<>();
            for (TreeNode node : levelNodes) {
                currentLevel.add(node.val);
                if (node.left != null) nextLevelNodes.add(node.left);
                if (node.right != null) nextLevelNodes.add(node.right);
            }
            traversal.add(0, currentLevel);
            levelNodes = nextLevelNodes;
        }
        return traversal;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> traversal = new LinkedList<>();
        if (root == null) return traversal;
        ArrayList<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root);
        while (!levelNodes.isEmpty()) {
            ArrayList<TreeNode> nextLevelNodes = new ArrayList<>();
            ArrayList<Integer> currentLevel = new ArrayList<>();
            for (TreeNode node : levelNodes) {
                currentLevel.add(node.val);
                if (node.left != null) nextLevelNodes.add(node.left);
                if (node.right != null) nextLevelNodes.add(node.right);
            }
            traversal.add(currentLevel);
            levelNodes = nextLevelNodes;
        }
        return traversal;
    }

    /**
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     * <p>
     * For example:
     * Given the following binary tree,
     * 1            <---
     * /   \
     * 2     3         <---
     * \     \
     * 5     4       <---
     * You should return [1, 3, 4].
     *
     * @param root a binary tree
     * @return the right side of the tree
     */
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> rightSide = new ArrayList<>();
        if (root == null) return rightSide;
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            ArrayList<TreeNode> nextLevel = new ArrayList<>();
            rightSide.add(nodes.get(nodes.size() - 1).val);
            for (TreeNode point : nodes) {
                if (point.left != null) nextLevel.add(point.left);
                if (point.right != null) nextLevel.add(point.right);
            }
            nodes = nextLevel;
        }
        return rightSide;
    }
}
