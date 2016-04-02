package LeetCode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

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

    /**
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
     * along the path equals the given sum.
     * https://leetcode.com/problems/path-sum/
     *
     * @param root a binary tree
     * @param sum  the target sum value
     * @return whether this tree contains such sum or not
     */
    public boolean hasPathSumDFS(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.add(root);
        sumStack.add(root.val);
        while (!nodeStack.isEmpty()) {
            TreeNode frontier = nodeStack.pop();
            int temp = sumStack.pop();
            if (frontier.left == null && frontier.right == null && temp == sum) return true;
            else if (temp != sum) {
                if (frontier.left != null) {
                    nodeStack.push(frontier.left);
                    sumStack.push(temp + frontier.left.val);
                }
                if (frontier.right != null) {
                    nodeStack.push(frontier.right);
                    sumStack.push(temp + frontier.right.val);
                }
            }
        }
        return false;
    }
}
