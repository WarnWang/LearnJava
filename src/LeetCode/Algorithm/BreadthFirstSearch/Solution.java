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
}
