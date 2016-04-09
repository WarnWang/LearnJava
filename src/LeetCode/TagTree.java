package LeetCode;

import java.util.*;

/**
 * Created by warn on 2/4/2016.
 * Use to store puzzles with tag Tree
 */
public class TagTree {
    int[] dp = null;
    private int maxValue = Integer.MIN_VALUE;

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

    /**
     * Given a binary tree, find the maximum path sum.
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
     * along the parent-child connections. The path does not need to go through the root.
     * https://leetcode.com/problems/binary-tree-maximum-path-sum/
     *
     * @param root a binary tree
     * @return the maximum sum of path
     */
    public int maxPathSum(TreeNode root) {
        maxPathSumAndBranch(root);
        return maxValue;
    }

    private int maxPathSumAndBranch(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) {
            maxValue = Integer.max(root.val, maxValue);
            return root.val;
        } else {
            int leftBranch;
            int rightBranch;
            if (root.left != null) leftBranch = maxPathSumAndBranch(root.left);
            else {
                rightBranch = maxPathSumAndBranch(root.right);
                int maxBranch = root.val + Integer.max(rightBranch, 0);
                maxValue = Integer.max(maxValue, maxBranch);
                return maxBranch;
            }
            if (root.right != null) rightBranch = maxPathSumAndBranch(root.right);
            else {
                int maxBranch = Integer.max(leftBranch, 0) + root.val;
                maxValue = Integer.max(maxValue, maxBranch);
                return maxBranch;
            }
            int maxBranch = Integer.max(leftBranch, rightBranch);
            maxBranch = Integer.max(maxBranch, 0) + root.val;
            int currentSub = Integer.max(leftBranch, 0) + Integer.max(rightBranch, 0) + root.val;
            maxValue = Integer.max(maxValue, currentSub);
            return maxBranch;
        }
    }

    /**
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
     * https://leetcode.com/problems/unique-binary-search-trees/
     *
     * @param n nodes number
     * @return how many BST tree exists
     */
    public int numTrees(int n) {
        if (n <= 2) return n;
        Set<BinarySearchTree> binaryTreeSet = new HashSet<>();
        Stack<ArrayList<Integer>> numStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            numStack.push(new ArrayList<>(Collections.singletonList(i + 1)));
        }
        while (!numStack.isEmpty()) {
            ArrayList<Integer> frontier = numStack.pop();
            if (frontier.size() == n) {
                BinarySearchTree temp = new BinarySearchTree(frontier.get(0));
                for (int i = 1; i < n; i++) {
                    temp.insert(frontier.get(i));
                }
                System.out.println(temp.toString());
                binaryTreeSet.add(temp);
            } else {
                Set<Integer> nextArraySet = new HashSet<>();
                for (int i = 0; i < n; i++) {
                    nextArraySet.add(i + 1);
                }
                nextArraySet.removeAll(frontier);
                for (int i : nextArraySet) {
                    ArrayList<Integer> nextExplorer = new ArrayList<>(frontier);
                    nextExplorer.add(i);
                    numStack.push(nextExplorer);
                }
            }
        }

        return binaryTreeSet.size();
    }

    public int numTreesBetter(int n) {
        if (n <= 1) return 1;
        if (dp == null) {
            dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
        } else if (dp[n] != 0) return dp[n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int numI = (dp[i] == 0) ? numTreesBetter(i) : dp[i];
            int numJ = (dp[n - i - 1] == 0) ? numTreesBetter(n - i - 1) : dp[n - i - 1];
            answer += numI * numJ;
        }
        dp[n] = answer;
        return answer;
    }

    private class BinarySearchTree {
        Node root;

        BinarySearchTree(int val) {
            root = new Node(val);
        }

        void insert(int val) {
            Node temp = root;
            while (true) {
                if (val > temp.value) {
                    if (temp.rightChild != null) temp = temp.rightChild;
                    else {
                        temp.rightChild = new Node(val);
                        break;
                    }
                } else {
                    if (temp.leftChild != null) temp = temp.leftChild;
                    else {
                        temp.leftChild = new Node(val);
                        break;
                    }
                }
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            Node temp = root;
            ArrayDeque<Node> tempQueue = new ArrayDeque<>();
            tempQueue.add(temp);
            stringBuilder.append(temp.value);
            while (!tempQueue.isEmpty()) {
                Node frontier = tempQueue.removeFirst();
                if (frontier.leftChild != null) {
                    stringBuilder.append(frontier.leftChild.value);
                    tempQueue.add(frontier.leftChild);
                } else stringBuilder.append('X');

                if (frontier.rightChild != null) {
                    stringBuilder.append(frontier.rightChild.value);
                    tempQueue.add(frontier.rightChild);
                } else stringBuilder.append('X');
            }
            return stringBuilder.toString();
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return toString().equals(obj.toString());
        }

        class Node {
            int value;
            Node leftChild = null, rightChild = null;

            Node(int val) {
                value = val;
            }
        }
    }
}
