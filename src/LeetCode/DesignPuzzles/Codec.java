package LeetCode.DesignPuzzles;

import LeetCode.DataTypes.TreeNode;

/**
 * Created by warn on 21/7/2016.
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * <p>
 * For example, you may serialize the following tree
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need
 * to follow this format, so please be creative and come up with different approaches yourself.
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder temp = new StringBuilder();
        toSerialize(root, temp);
        return temp.toString();
    }

    private void toSerialize(TreeNode root, StringBuilder temp) {
        if (root == null) temp.append('n');
        else {
            temp.append(root.val);
            temp.append(',');
            toSerialize(root.left, temp);
            temp.append(',');
            toSerialize(root.right, temp);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] info = data.split(",");
        if (info.length >= 3) {
            index = 0;
            TreeNode root;
            root = new TreeNode(Integer.parseInt(info[0]));
            deserialize(info, root);
            return root;
        }
        else return null;
    }

    private int index;

    private void deserialize(String[] info, TreeNode root){
        if (index >= info.length - 2) return;
        if (!info[++index].equals("n")) {
            root.left = new TreeNode(Integer.parseInt(info[index]));
            deserialize(info, root.left);
        }

        if (!info[++index].equals("n")) {
            root.right = new TreeNode(Integer.parseInt(info[index]));
            deserialize(info, root.right);
        }
    }

    public static void main(String[] args){
        Codec test = new Codec();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(2);
        System.out.println(test.serialize(root));
        TreeNode temp = test.deserialize(test.serialize(root));
        System.out.println("hahah");
    }
}
