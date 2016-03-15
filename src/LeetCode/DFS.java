package LeetCode;

import java.util.Stack;

/**
 * Created by warn on 15/3/2016.
 * Use to store DFS search algorithm problem
 */
public class DFS {
    /**
     * Given two binary trees, write a function to check if they are equal or not.
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     * Tag: Tree, DFS
     *
     * @param p tree A
     * @param q tree B
     * @return whether same or not
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> pStack = new Stack<>();
        Stack<TreeNode> qStack = new Stack<>();
        pStack.add(p);
        qStack.add(q);
        while (!pStack.isEmpty() && !qStack.isEmpty()) {
            p = pStack.pop();
            q = qStack.pop();
            if (p == null && q == null) continue;
            if (p == null || q == null || p.val != q.val) return false;
            else {
                pStack.push(p.left);
                pStack.push(p.right);
                qStack.push(q.left);
                qStack.push(q.right);
            }
        }
        return true;
    }

    public boolean isSameTreeRecursively(TreeNode p, TreeNode q) {
        return p == null && q == null || !(p == null || q == null || p.val != q.val) && true &&
                isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
