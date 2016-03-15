package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by warn on 15/3/2016.
 * Use to store some BFS
 */
public class BFS {
    /**
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
     * Given binary tree {3,9,20,#,#,15,7},
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     *
     * @param root a root node of a tree
     * @return the zigzag level order list
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzagList = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        boolean reversed = true;
        if (root == null) return zigzagList;
        else {
            nodeQueue.add(root);
            levelQueue.add(0);
        }

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int level = levelQueue.remove();
            List<Integer> levelList;
            if (zigzagList.size() == level) {
                levelList = new LinkedList<>();
                levelList.add(node.val);
                zigzagList.add(levelList);
                reversed = !reversed;
            } else {
                levelList = zigzagList.get(level);
                if (reversed) levelList.add(0, node.val);
                else levelList.add(node.val);
            }

            if (node.left != null) {
                nodeQueue.add(node.left);
                levelQueue.add(level + 1);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                levelQueue.add(level + 1);
            }
        }
        return zigzagList;
    }
}
