package LeetCode;

import java.util.*;

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
//        System.out.print(test.isValidSerialization("#1##"));
        System.out.print(test.fractionToDecimal(-5, 6));
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


    public String fractionToDecimal(int numerator, int denominator) {
        String result;

        if (numerator % denominator == 0) result = Long.toString((long) numerator / (long) denominator);
        else {
            StringBuilder fractionList = new StringBuilder();
            ArrayList<Long> moduleList = new ArrayList<>();
            int integerPart = Math.abs(numerator / denominator);
            long module = numerator % denominator;
            long fraction = Math.abs(module * 10 / denominator);
            moduleList.add(module);
            while (module != 0) {
                fractionList.append(fraction);
                module = (module * 10) % denominator;
                fraction = Math.abs(module * 10 / denominator);
                if (moduleList.contains(module)) {
                    int index = moduleList.indexOf(module);
                    fractionList.append(")");
                    fractionList.insert(index, "(");
                    break;
                }
                moduleList.add(module);
            }
            if (denominator < 0 ^ numerator < 0) result = "-" + Integer.toString(integerPart) + "." + fractionList;
            else result = Integer.toString(integerPart) + "." + fractionList;
        }
        return result;
    }

    public String fractionToDecimal2(int numerator, int denominator) {

        if (numerator == 0)
            return "0";
        if (denominator == 0)
            return "";

        StringBuilder result = new StringBuilder();

        // is result is negative
        if ((numerator < 0) ^ (denominator < 0)) {
            result.append("-");
        }

        // convert int to long
        long num = numerator, den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);

        // quotient
        long res = num / den;
        result.append(String.valueOf(res));

        // if remainder is 0, return result
        long remainder = (num % den) * 10;
        if (remainder == 0)
            return result.toString();

        // right-hand side of decimal point
        HashMap<Long, Integer> map = new HashMap<>();
        result.append(".");
        while (remainder != 0) {
            // if digits repeat
            if (map.containsKey(remainder)) {
                int beg = map.get(remainder);
                String part1 = result.substring(0, beg);
                String part2 = result.substring(beg, result.length());
                return part1 + "(" + part2 + ")";
            }

            // continue
            map.put(remainder, result.length());
            res = remainder / den;
            result.append(String.valueOf(res));
            remainder = (remainder % den) * 10;
        }

        return result.toString();
    }
}