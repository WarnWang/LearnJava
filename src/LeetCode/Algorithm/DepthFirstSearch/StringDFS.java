package LeetCode.Algorithm.DepthFirstSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by warn on 20/8/2016.
 * Some thing related to tire
 */
public class StringDFS {

    /**
     * Given a 2D board and a list of words from the dictionary, find all words in the board.
     * <p>
     * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
     * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
     * <p>
     * For example,
     * Given words = ["oath","pea","eat","rain"] and board =
     * <p>
     * [
     * ['o','a','a','n'],
     * ['e','t','a','e'],
     * ['i','h','k','r'],
     * ['i','f','l','v']
     * ]
     * Return ["eat","oath"].
     *
     * @param board a 2D board
     * @param words a list of words from the dictionary
     * @return all used word
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> resultList = new ArrayList<>();
        if (words == null || words.length == 0 || board == null || board.length == 0 || board[0].length == 0)
            return resultList;
        Tire wordTire = new Tire();
        int maxWordLength = 0;
        for (String word : words) {
            wordTire.addWord(word);
            if (word != null) maxWordLength = Math.max(maxWordLength, word.length());
        }
        char[] path = new char[maxWordLength];
        HashSet<String> resultSet = new HashSet<>();
        for (int i = 0, w = board.length; i < w; i++) {
            for (int j = 0, h = board[i].length; j < h; j++) {
                if (wordTire.startsWith(board[i][j])) {
                    char c = board[i][j];
                    path[0] = c;
                    board[i][j] = '#';
                    dfsStringSearch(board, path, 1, wordTire, resultSet, i, j);
                    board[i][j] = c;
                }
            }
        }
        resultList.addAll(resultSet);
        return resultList;
    }

    private void dfsStringSearch(char[][] board, char[] path, int pathIndex, Tire wordTire, HashSet<String> result,
                                 int boardIndexX, int boardIndexY) {
        if (pathIndex >= path.length) {
            if (wordTire.containWord(path)) result.add(new String(path));
            return;
        }
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int newX = boardIndexX + direction[0];
            int newY = boardIndexY + direction[1];
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length) {
                char c = board[newX][newY];
                if (c == '#') continue;
                path[pathIndex] = c;
                int code = wordTire.getPrefixType(path, 0, pathIndex + 1);

                if ((code & 1) == 1) result.add(new String(path, 0, pathIndex + 1));
                if ((code & 2) == 2) {
                    board[newX][newY] = '#';
                    dfsStringSearch(board, path, pathIndex + 1, wordTire, result,
                            direction[0], direction[1]);
                    board[newX][newY] = c;
                }
            }
        }
    }

    private class TireNode {
        HashMap<Character, TireNode> characterTireNodeHashMap;
        boolean isEnd;

        TireNode() {
            characterTireNodeHashMap = new HashMap<>();
            isEnd = false;
        }
    }

    private class Tire {
        TireNode root;

        Tire() {
            root = new TireNode();
        }

        void addWord(String word) {
            if (word == null || word.length() == 0) root.isEnd = true;
            else {
                char[] wordArray = word.toCharArray();
                TireNode pointer = root;
                for (char c : wordArray) {
                    if (!pointer.characterTireNodeHashMap.containsKey(c))
                        pointer.characterTireNodeHashMap.put(c, new TireNode());
                    pointer = pointer.characterTireNodeHashMap.get(c);
                }
                pointer.isEnd = true;
            }
        }

        boolean containWord(String word) {
            if (word == null || word.length() == 0) return root.isEnd;
            TireNode end = getMatchTriNode(word.toCharArray(), 0, word.length());
            return (end != null) && end.isEnd;
        }

        boolean containWord(char[] wordArray) {
            return containWord(wordArray, 0, wordArray.length);
        }

        boolean containWord(char[] wordArray, int end) {
            return containWord(wordArray, 0, end);
        }

        boolean containWord(char[] wordArray, int start, int end) {
            if (wordArray == null || wordArray.length == 0 || start >= end) return root.isEnd;
            TireNode endNode = getMatchTriNode(wordArray, start, end);
            return (endNode != null) && endNode.isEnd;
        }

        boolean startsWith(char c) {
            return root.characterTireNodeHashMap.containsKey(c);
        }

        boolean isPrefix(String prefix) {
            if (prefix == null || prefix.length() == 0) return true;
            TireNode end = getMatchTriNode(prefix.toCharArray(), 0, prefix.length());
            return end != null;
        }

        /**
         * check a word contains in this tire or not
         *
         * @param word word to check
         * @return 0 for not prefix and not word
         * 1 for word only
         * 2 for prefix only
         * 3 for both word and prefix
         */
        int getPrefixType(String word) {
            if (word == null || word.length() == 0) {
                if (root.isEnd) return 3;
                else return 2;
            }
            TireNode end = getMatchTriNode(word.toCharArray(), 0, word.length());
            if (end == null) return 0;
            else if (end.isEnd) {
                if (end.characterTireNodeHashMap.isEmpty()) return 1;
                else return 3;
            } else return 2;
        }

        int getPrefixType(char[] wordArray, int start, int end) {
            if (wordArray == null || wordArray.length == 0 || start >= end) {
                if (root.isEnd) return 3;
                else return 2;
            }
            TireNode endNode = getMatchTriNode(wordArray, start, end);
            if (endNode == null) return 0;
            else if (endNode.isEnd) {
                if (endNode.characterTireNodeHashMap.isEmpty()) return 1;
                else return 3;
            } else return 2;
        }

        private TireNode getMatchTriNode(char[] wordArray, int start, int end) {
            TireNode pointer = root;
            for (int i = start; i < end; i++) {
                char c = wordArray[i];
                if (pointer.characterTireNodeHashMap.containsKey(c)) pointer = pointer.characterTireNodeHashMap.get(c);
                else return null;
            }
            return pointer;
        }
    }
}
