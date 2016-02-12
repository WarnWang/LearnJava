package LeetCode;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

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

    public static void main(String[] args) {
        // put your codes here
        TreeNode test = new TreeNode(1);
        System.out.print(test.isValidSerialization("#1##"));
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

    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            root.left = invertTree(root.left);
            root.right = invertTree(root.right);
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        return root;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        Stack<TreeNode> nodes = new Stack<>();
        Stack<TreeNode> path = new Stack<>();
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        nodes.add(root);
        while (!nodes.isEmpty()) {
            TreeNode frontier = nodes.pop();
            path.push(frontier);
            if (frontier.right != null) {
                nodes.push(frontier.right);
            }
            if (frontier.left != null) {
                nodes.push(frontier.left);
            }
            if (frontier.left == null && frontier.right == null) {
                String[] pathValue = new String[path.size()];
                for (int i = 0; i < pathValue.length; i++) pathValue[i] = Integer.toString(path.get(i).val);
                result.add(String.join("->", pathValue));
                TreeNode tempNode = path.pop();
                while (path.size() >= 1) {
                    TreeNode currentNode = path.pop();
                    if (currentNode.left == tempNode && currentNode.right != null) {
                        path.push(currentNode);
                        break;
                    }
                    tempNode = currentNode;
                }
            }
        }
        return result;
    }

    public boolean isValidSerialization(String preorder) {
        Stack<String> treeNodes = new Stack<>();
        String[] strings = preorder.split(",");
        for (String temp : strings) {
            while (temp.equals("#")) {
                try {
                    String lastCharacter = treeNodes.pop();
                    if (!lastCharacter.equals(temp)) {
                        treeNodes.push(lastCharacter);
                        break;
                    } else {
                        if (treeNodes.size() > 0) {
                            lastCharacter = treeNodes.pop();
                            if (lastCharacter.equals(temp)) return false;
                        } else return false;
                    }
                } catch (EmptyStackException e) {
                    break;
                }
            }
            treeNodes.push(temp);
        }
        return treeNodes.size() == 1 && treeNodes.pop().equals("#");
    }
}