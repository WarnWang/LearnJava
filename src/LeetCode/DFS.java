package LeetCode;

import java.util.*;

/**
 * Created by warn on 15/3/2016.
 * Use to store DFS search algorithm problem
 */
public class DFS {
    /**
     * Given two binary trees, write a function to check if they are equal or not.
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     * Tag: Tree, DFS
     * Difficulty: easy
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
        return p == null && q == null || !(p == null || q == null || p.val != q.val) &&
                isSameTreeRecursively(p.left, q.left) && isSameTreeRecursively(p.right, q.right);
    }

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     *
     * @param s a string
     * @return all possible palindrome partitioning of s
     */
    public List<List<String>> partition(String s) {
        List<List<String>> partitionList = new ArrayList<>();
        partition(new ArrayList<>(), s, partitionList);
        return partitionList;
    }

    private void partition(List<String> frontier, String remainder, List<List<String>> partitionList) {
        if (remainder == null || remainder.isEmpty()) {
            if (!frontier.isEmpty()) partitionList.add(frontier);
        } else {
            for (int i = 1; i <= remainder.length(); i++) {
                String newRemainder = remainder.substring(i);
                String possibleFrontier = remainder.substring(0, i);
                if (isPalindrome(possibleFrontier)) {
                    ArrayList<String> newFrontier = new ArrayList<>(frontier);
                    newFrontier.add(possibleFrontier);
                    partition(newFrontier, newRemainder, partitionList);
                }
            }
        }
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    // Dynamic programming version
    public List<List<String>> partitionDynamicProgramming(String s) {
        Set<List<String>> partitionList = new HashSet<>();
        partitionList.add(new ArrayList<>(Collections.singletonList(s.substring(0, 1))));
        for (int i = 1; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            List<List<String>> newList = new ArrayList<>();
            for (List<String> currentList: partitionList) {
                String tempString = Character.toString(currentChar);
                for (int j = currentList.size() - 1; j >= 0; j--) {
                    tempString = currentList.get(j) + tempString;
                    if (isPalindrome(tempString)) {
                        List<String> newAnswer = new ArrayList<>();
                        for (int k = 0; k < j; k++) {
                            newAnswer.add(currentList.get(k));
                        }
                        newAnswer.add(tempString);
                        newList.add(newAnswer);
                    }
                }
                currentList.add(Character.toString(currentChar));
            }
            partitionList.addAll(newList);
        }
        return new ArrayList<>(partitionList);
    }
}
