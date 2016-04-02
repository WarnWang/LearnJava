package LeetCode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by warn on 2/4/2016.
 * Use to store puzzles with tag Tree
 */
public class TagTree {

    /**
     * Given a binary tree, find its minimum depth.
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf
     * node.
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/
     *
     * @param root a binary tree
     * @return the minimum depth
     */
    public int minDepthBFS(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> nodesQueue = new ArrayDeque<>();
        Queue<TreeNode> nextLayerNode = new ArrayDeque<>();
        nodesQueue.add(root);
        int depth = 1;
        while (!nodesQueue.isEmpty()) {
            TreeNode frontier = nodesQueue.remove();
            if (frontier.left == null && frontier.right == null) break;
            else {
                if (frontier.left != null) nextLayerNode.add(frontier.left);
                if (frontier.right != null) nextLayerNode.add(frontier.right);
            }
            if (nodesQueue.isEmpty() && !nextLayerNode.isEmpty()) {
                depth++;
                nodesQueue = nextLayerNode;
                nextLayerNode = new ArrayDeque<>();
            }
        }
        return depth;
    }
}
