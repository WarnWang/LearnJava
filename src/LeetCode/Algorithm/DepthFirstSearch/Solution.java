package LeetCode.Algorithm.DepthFirstSearch;

import java.util.*;

/**
 * Created by warn on 3/6/2016.
 * Store solution to complete binary tree
 */
public class Solution {

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * <p>
     * Return all possible palindrome partitioning of s.
     * <p>
     * For example, given s = "aab",
     * Return
     * <p>
     * [
     * ["aa","b"],
     * ["a","a","b"]
     * ]
     *
     * @param s a string
     * @return all possible palindrome partitioning of s
     */
    private int[][] stringMap;

    /**
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
     * other.
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both
     * indicate a queen and an empty space respectively.
     * For example,
     * There exist two distinct solutions to the 4-queens puzzle:
     * <p>
     * [
     * [".Q..",  // Solution 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * ["..Q.",  // Solution 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     *
     * @param n how many queues
     * @return the list of all solution to n-queues
     */
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return null;
        List<List<String>> solutions = new ArrayList<>();

        // represent the board, used to represent which position in the board is being attacked.
        boolean[][] isAttacked = new boolean[n][n];

        // An array to mark that the Y position of each queen in every row.
        int[] queenYPosition = new int[n];
        solveNQueens(0, n, isAttacked, queenYPosition, solutions);
        return solutions;
    }

    private void solveNQueens(int index, int nQueens, boolean[][] isAttacked, int[] queenYPosition,
                              List<List<String>> solutions) {
        if (index == nQueens) solutions.add(generateSolutionFromQueenYPosition(queenYPosition, nQueens));
        else {
            ArrayList<int[]> changedBoard = new ArrayList<>();
            for (int i = 0; i < nQueens; i++) {
                if (!isAttacked[index][i]) {
                    changedBoard.clear();
                    changeAttackList(isAttacked, index, i, nQueens, changedBoard);
                    queenYPosition[index] = i;
                    solveNQueens(index + 1, nQueens, isAttacked, queenYPosition, solutions);
                    recoverIsAttackedArray(isAttacked, changedBoard);
                }
            }
        }
    }

    private List<String> generateSolutionFromQueenYPosition(int[] queenYPosition, int nQueens) {
        ArrayList<String> solution = new ArrayList<>(nQueens);
        for (int i = 0; i < nQueens; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < nQueens; j++) {
                if (queenYPosition[i] == j) row.append('Q');
                else row.append('.');
            }
            solution.add(row.toString());
        }
        return solution;
    }

    private void changeAttackList(boolean[][] isAttacked, int x, int y, int nQueens, ArrayList<int[]> changedIndex) {
        // add all positions that being attacked below x, y to is attacked array, as I did this from top to bottom,
        // there is no need to change values in the same and above row.
        for (int i = x + 1; i < nQueens; i++) {
            if (!isAttacked[i][y]) {
                isAttacked[i][y] = true;
                changedIndex.add(new int[]{i, y});
            }
        }

        for (int i = x + 1, j = y + 1; i < nQueens && j < nQueens; i++, j++) {
            if (!isAttacked[i][j]) {
                isAttacked[i][j] = true;
                changedIndex.add(new int[]{i, j});
            }
        }

        for (int i = x + 1, j = y - 1; i < nQueens && j > -1; i++, j--) {
            if (!isAttacked[i][j]) {
                isAttacked[i][j] = true;
                changedIndex.add(new int[]{i, j});
            }
        }
    }

    private void recoverIsAttackedArray(boolean[][] isAttacked, ArrayList<int[]> changedIndex) {
        for (int[] changedPosition : changedIndex) isAttacked[changedPosition[0]][changedPosition[1]] = false;
    }

    // https://leetcode.com/discuss/102798/java-easy-to-understand-recursive-solution
    public List<List<String>> solveNQueensBetterValidation(int n) {
        if (n <= 0) return null;
        List<List<String>> solutions = new ArrayList<>();
        solveNQueensBetterValidation(0, n, new int[n], new LinkedList<>(), solutions);
        return solutions;
    }

    private void solveNQueensBetterValidation(int row, int n, int[] colPosition, LinkedList<String> path,
                                              List<List<String>> solutions) {
        if (row == n) solutions.add(new ArrayList<>(path));
        else {
            for (int i = 0; i < n; i++) {
                if (checkValid(row, i, colPosition)) {
                    colPosition[row] = i;
                    path.add(String.join("", Collections.nCopies(i, ".")) + 'Q'
                            + String.join("", Collections.nCopies(n - i - 1, ".")));
                    solveNQueensBetterValidation(row + 1, n, colPosition, path, solutions);
                    path.removeLast();
                }
            }
        }
    }

    private boolean checkValid(int row, int col, int[] colPosition) {
        for (int i = 0; i < row; i++) {
            if (colPosition[i] == col || Math.abs(colPosition[i] - col) == row - i) return false;
        }
        return true;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> partitionList = new ArrayList<>();
        if (s == null || s.length() == 0) return partitionList;
        stringMap = new int[s.length()][s.length()];
        partition(s, 0, partitionList, new ArrayList<>());
        return partitionList;
    }

    private void partition(String s, int startIndex, List<List<String>> partitions, List<String> path) {
        if (startIndex >= s.length()) partitions.add(path);
        for (int i = startIndex, n = s.length(); i < n; i++) {
            if (isPalindrome(s, startIndex, i)) {
                ArrayList<String> newPath = new ArrayList<>(path);
                newPath.add(s.substring(startIndex, i + 1));
                partition(s, i + 1, partitions, newPath);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        if (start == end || stringMap[start][end] == 1) return true;
        else if (stringMap[start][end] == -1) return false;
        int i = start, j = end;
        while (i < j) {
            if (stringMap[i][j] == -1) {
                stringMap[start][end] = -1;
                return false;
            } else if (stringMap[i][j] == 1) {
                stringMap[start][end] = 1;
                return true;
            } else if (s.charAt(i) != s.charAt(j)) {
                stringMap[i][j] = -1;
                stringMap[start][end] = -1;
                return false;
            } else {
                i++;
                j--;
            }
        }
        stringMap[start][end] = 1;
        return true;
    }

    /**
     * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
     * results.
     * <p>
     * Note: The input string may contain letters other than the parentheses ( and ).
     * <p>
     * Examples:
     * "()())()" -> ["()()()", "(())()"]
     * "(a)())()" -> ["(a)()()", "(a())()"]
     * ")(" -> [""]
     *
     * @param s a string of parentheses
     * @return the minimum number of invalid parentheses
     */
    public List<String> removeInvalidParentheses(String s) {
        maxLength = 0;
        ArrayList<String> results = new ArrayList<>();
        HashSet<String> temp = new HashSet<>();
        temp.add("");
        if (s != null && s.length() > 0) {
            removeInvalidParentheses(s.toCharArray(), 0, new char[s.length()], 0, temp);
        }
        for (String str: temp) {
            if (str.length() == maxLength) results.add(str);
        }
        return results;
    }

    private int maxLength;

    private void removeInvalidParentheses(char[] s, int index, char[] path, int end, HashSet<String> result) {
        if (end >= maxLength && isValidParentheses(path, end)) {
            result.add(new String(path, 0, end));
            maxLength = end;
        }
        if (index < s.length){
            for (int i = index; i < s.length; i++) {
                if (s.length - i + end < maxLength) return;
                path[end] = s[i];
                removeInvalidParentheses(s, i + 1, path, end + 1, result);
            }
        }
    }

    private boolean isValidParentheses(char[] path, int length) {
        int n = 0;
        for (int i = 0; i < length; i++) {
            char c = path[i];
            switch (c){
                case '(':
                    n++;
                    break;
                case ')':
                    if (n < 1) return false;
                    n--;
                    break;
            }
        }
        return n == 0;
    }
}