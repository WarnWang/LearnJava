package LeetCode;

/**
 * Created by warn on 8/2/2016.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 1;
        else {
            int maxRight = 0;
            int maxLeft = 0;
            if (root.left != null) {
                maxLeft = maxDepth(root.left);
            }

            if (root.right != null) {
                maxRight = maxDepth(root.right);
            }
            return 1 + Integer.max(maxLeft, maxRight);
        }
    }

    public static void main(String[] args) {
        // put your codes here
    }
}