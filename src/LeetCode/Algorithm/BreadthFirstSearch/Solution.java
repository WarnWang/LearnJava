package LeetCode.Algorithm.BreadthFirstSearch;

import LeetCode.DataTypes.TreeNode;

import java.util.*;

/**
 * Created by warn on 9/6/2016.
 * Solution to breadth first search puzzles
 */
public class Solution {

    private boolean connect;

    /**
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
     * <p>
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its bottom-up level order traversal as:
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     *
     * @param root a binary tree
     * @return bottom-up level order traversal of the binary tree
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> traversal = new LinkedList<>();
        if (root == null) return traversal;
        ArrayList<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root);
        while (!levelNodes.isEmpty()) {
            ArrayList<TreeNode> nextLevelNodes = new ArrayList<>();
            ArrayList<Integer> currentLevel = new ArrayList<>();
            for (TreeNode node : levelNodes) {
                currentLevel.add(node.val);
                if (node.left != null) nextLevelNodes.add(node.left);
                if (node.right != null) nextLevelNodes.add(node.right);
            }
            traversal.add(0, currentLevel);
            levelNodes = nextLevelNodes;
        }
        return traversal;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> traversal = new LinkedList<>();
        if (root == null) return traversal;
        ArrayList<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root);
        while (!levelNodes.isEmpty()) {
            ArrayList<TreeNode> nextLevelNodes = new ArrayList<>();
            ArrayList<Integer> currentLevel = new ArrayList<>();
            for (TreeNode node : levelNodes) {
                currentLevel.add(node.val);
                if (node.left != null) nextLevelNodes.add(node.left);
                if (node.right != null) nextLevelNodes.add(node.right);
            }
            traversal.add(currentLevel);
            levelNodes = nextLevelNodes;
        }
        return traversal;
    }

    /**
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     * <p>
     * For example:
     * Given the following binary tree,
     * 1            <---
     * /   \
     * 2     3         <---
     * \     \
     * 5     4       <---
     * You should return [1, 3, 4].
     *
     * @param root a binary tree
     * @return the right side of the tree
     */
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> rightSide = new ArrayList<>();
        if (root == null) return rightSide;
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            ArrayList<TreeNode> nextLevel = new ArrayList<>();
            rightSide.add(nodes.get(nodes.size() - 1).val);
            for (TreeNode point : nodes) {
                if (point.left != null) nextLevel.add(point.left);
                if (point.right != null) nextLevel.add(point.right);
            }
            nodes = nextLevel;
        }
        return rightSide;
    }

    /**
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence
     * of one or more dictionary words.
     * <p>
     * For example, given
     * s = "leetcode",
     * dict = ["leet", "code"].
     * <p>
     * Return true because "leetcode" can be segmented as "leet code".
     *
     * @param s        a string s
     * @param wordDict a dictionary of words dict
     * @return if s can be segmented into a space-separated sequence of one or more dictionary words
     */
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0) return true;
        else if (wordDict == null || wordDict.size() == 0) return false;
        int n = s.length();
        char[] sArray = s.toCharArray();
        int[] wordLengthArray = new int[wordDict.size()];
        int index = 0;
        for (String str : wordDict) wordLengthArray[index++] = str.length();
        ArrayList<Integer> queryQueue = new ArrayList<>(Collections.singletonList(0));
        boolean[] isVisited = new boolean[n];
        while (!queryQueue.isEmpty()) {
            ArrayList<Integer> tempQueue = new ArrayList<>();
            for (int start : queryQueue) {
                for (int wordLength : wordLengthArray) {
                    if (wordLength + start > n) continue;
                    if (wordDict.contains(new String(sArray, start, wordLength))) {
                        if (start + wordLength == n) return true;
                        else if (!isVisited[start + wordLength]) {
                            isVisited[start + wordLength] = true;
                            tempQueue.add(start + wordLength);
                        }
                    }
                }
            }
            queryQueue = tempQueue;
        }
        return false;
    }

    /**
     * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation
     * sequence(s) from beginWord to endWord, such that:
     * <p>
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the word list
     * For example,
     * <p>
     * Given:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * Return
     * [
     * ["hit","hot","dot","dog","cog"],
     * ["hit","hot","lot","log","cog"]
     * ]
     * Note:
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     *
     * @param beginWord beginning word
     * @param endWord   ending word
     * @param wordList  possible intermediate word
     * @return a word path
     */
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> ladderList = new ArrayList<>();
        if (wordList == null || beginWord == null || endWord == null || beginWord.length() == 0 ||
                endWord.length() == 0 || wordList.size() == 0 || beginWord.length() != endWord.length() ||
                !wordList.contains(beginWord) || !wordList.contains(endWord))
            return ladderList;
        HashMap<String, List<String>> wordMap = new HashMap<>();
        connect = false;
        findShortestPathDistance(new HashSet<>(Collections.singletonList(beginWord)),
                new HashSet<>(Collections.singletonList(endWord)), wordList, wordMap, true);
        if (connect) {
            getShortestPath(new ArrayList<>(Collections.singletonList(beginWord)), wordMap, ladderList, beginWord,
                    endWord);
        }
        return ladderList;
    }

    private void findShortestPathDistance(Set<String> firstSet, Set<String> secondSet, Set<String> wordList,
                                          HashMap<String, List<String>> wordMap, boolean forward) {
        if (firstSet.size() > secondSet.size()) {
            findShortestPathDistance(secondSet, firstSet, wordList, wordMap, !forward);
            return;
        }

        wordList.removeAll(firstSet);
        wordList.removeAll(secondSet);
        HashSet<String> thirdSet = new HashSet<>();
        for (String word : firstSet) {
            char[] wordArray = word.toCharArray();
            for (int i = 0, n = wordArray.length; i < n; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == wordArray[i]) continue;
                    char tmp = wordArray[i];
                    wordArray[i] = c;
                    String newString = new String(wordArray);
                    if (secondSet.contains(newString)) {
                        connect = true;

                        String key = (forward) ? word : newString;
                        String value = (forward) ? newString : word;
                        if (wordMap.containsKey(key)) wordMap.get(key).add(value);
                        else wordMap.put(key, new ArrayList<>(Collections.singletonList(value)));
                    } else if (wordList.contains(newString)) {
                        thirdSet.add(newString);
                        String key = (forward) ? word : newString;
                        String value = (forward) ? newString : word;
                        if (wordMap.containsKey(key)) wordMap.get(key).add(value);
                        else wordMap.put(key, new ArrayList<>(Collections.singletonList(value)));
                    }

                    wordArray[i] = tmp;
                }
            }
        }
        if (!connect && !thirdSet.isEmpty()) {
            findShortestPathDistance(thirdSet, secondSet, wordList, wordMap, forward);
        }
    }

    private void getShortestPath(List<String> path, HashMap<String, List<String>> wordMap,
                                 List<List<String>> ladderList, String start, String end) {
        if (start.equals(end)) ladderList.add(new ArrayList<>(path));
        else if (wordMap.containsKey(start)) {
            for (String nextWord : wordMap.get(start)) {
                path.add(nextWord);
                getShortestPath(path, wordMap, ladderList, nextWord, end);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * <p>
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * But the following [1,2,2,null,3,null,3] is not:
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     *
     * @param root a binary tree
     * @return whether this is a symmetric tree
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        else if (root.left == null || root.right == null) return false;
        else if (root.left.val != root.right.val) return false;

        ArrayList<TreeNode> levelNode = new ArrayList<>();
        levelNode.add(root.right);
        levelNode.add(root.left);

        while (levelNode.isEmpty()) {
            int nodeLength = levelNode.size();

            ArrayList<TreeNode> nextLevelNode = new ArrayList<>();

            boolean[] hasNode = new boolean[nodeLength];

            for (int i = 0; i < nodeLength / 2; i++) {
                TreeNode tmp = levelNode.get(i);
                if (tmp.right != null) {
                    hasNode[2 * i] = true;
                    nextLevelNode.add(tmp.right);
                } else hasNode[2 * i] = false;

                if (tmp.left != null) {
                    hasNode[2 * i + 1] = true;
                    nextLevelNode.add(tmp.left);
                } else hasNode[2 * i + 1] = false;
            }

            int levelIndex = nextLevelNode.size() - 1;
            for (int i = nodeLength / 2; i < nodeLength; i++) {
                TreeNode tmp = levelNode.get(i);
                if (tmp.right != null) {
                    nextLevelNode.add(tmp.right);
                    if (!hasNode[2 * nodeLength - 1 - 2 * i] || tmp.right.val != nextLevelNode.get(levelIndex--).val)
                        return false;
                } else if (hasNode[2 * nodeLength - 1 - 2 * i]) return false;

                if (tmp.left != null) {
                    nextLevelNode.add(tmp.left);
                    if (!hasNode[2 * nodeLength - 2 - 2 * i] || tmp.left.val != nextLevelNode.get(levelIndex--).val)
                        return false;
                } else if (hasNode[2 * nodeLength - 2 - 2 * i]) return false;
            }
            for (TreeNode node: nextLevelNode) System.out.print(node.val + ", ");
            System.out.println();
            levelNode = nextLevelNode;
        }
        return true;
    }
}
