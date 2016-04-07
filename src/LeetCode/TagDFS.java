package LeetCode;

import java.util.*;

/**
 * Created by warn on 15/3/2016.
 * Use to store TagDFS search algorithm problem
 */
public class TagDFS {
    private int[][] palindromeFlag;

    /**
     * Given two binary trees, write a function to check if they are equal or not.
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     * Tag: Tree, TagDFS
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
        palindromeFlag = new int[s.length()][s.length()];
        partition(new ArrayList<>(), s, 0, partitionList);
        return partitionList;
    }

    private void partition(List<String> frontier, String s, int remainderIndex, List<List<String>> partitionList) {
        if (remainderIndex == s.length()) {
            if (!frontier.isEmpty()) partitionList.add(frontier);
        } else {
            for (int i = remainderIndex + 1; i <= s.length(); i++) {
                if (isPalindrome(s, remainderIndex, i - 1)) {
                    String possibleFrontier = s.substring(remainderIndex, i);
                    ArrayList<String> newFrontier = new ArrayList<>(frontier);
                    newFrontier.add(possibleFrontier);
                    partition(newFrontier, s, i, partitionList);
                }
            }
        }
    }

    private boolean isPalindrome(String s, int i, int j) {
        if (palindromeFlag[i][j] == 1 || i == j) return true;
        else if (palindromeFlag[i][j] == -1) return false;
        for (; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                palindromeFlag[i][j] = -1;
                return false;
            }
        }
        palindromeFlag[i][j] = 1;
        return true;
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
            for (List<String> currentList : partitionList) {
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

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by
     * water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of
     * the grid are all surrounded by water.
     * Also have a BFS version (but BFS TLE), a union find version.
     *
     * @param grid 2d grid map
     * @return the number of lands
     */
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        if (grid.length == 0) return 0;
        int[] gridSize = {grid.length, grid[0].length};
        int gridNum = gridSize[0] * gridSize[1];
        if (gridSize[1] == 0) return 0;
        int islandNum = 0;

        HashSet<Integer> exploredPosition = new HashSet<>();
        for (int i = 0; i < gridNum; i++) {
            if (exploredPosition.contains(i)) continue;
            if (grid[i / gridSize[1]][i % gridSize[1]] == '1') {
                islandNum++;
                exploreIsland(grid, exploredPosition, i, gridSize);
            }
        }
        return islandNum;
    }

    private void exploreIsland(char[][] grid, HashSet<Integer> exploredPos, int startPos, int[] gridSize) {
        if (exploredPos.contains(startPos)) return;
        exploredPos.add(startPos);
        int x = startPos / gridSize[1];
        int y = startPos % gridSize[1];
        for (int i = 0; i < 4; i++) {
            int nextX = x;
            int nextY = y;
            switch (i) {
                case 0:
                    if (--nextX < 0) continue;
                    break;
                case 1:
                    if (++nextX >= gridSize[0]) continue;
                    break;
                case 2:
                    if (--nextY < 0) continue;
                    break;
                default:
                    if (++nextY >= gridSize[1]) continue;
            }
            if (grid[nextX][nextY] == '1') exploreIsland(grid, exploredPos, nextX * gridSize[1] + nextY, gridSize);
            else exploredPos.add(nextX * gridSize[1] + nextY);
        }
    }

    /**
     * Detail can be found in UnionFindAlgorithm.solve
     *
     * @param board a board of 'X' and 'O'
     */
    public void solve(char[][] board) {
        if (board == null) return;
        int rows = board.length;
        if (rows <= 2) return;
        int cols = board[0].length;
        if (cols <= 2) return;

        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') solve(board, i, 0, '1', 'O');
            if (board[i][cols - 1] == 'O') solve(board, i, cols - 1, '1', 'O');
        }

        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O') solve(board, 0, i, '1', 'O');
            if (board[rows - 1][i] == 'O') solve(board, rows - 1, i, '1', 'O');
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '1') board[i][j] = 'O';
            }
        }
    }

    private void solve(char[][] board, int x, int y, char setTo, char origin) {
        board[x][y] = setTo;
        if (x + 1 < board.length && board[x + 1][y] == origin) solve(board, x + 1, y, setTo, origin);
        if (x - 1 >= 0 && board[x - 1][y] == origin) solve(board, x - 1, y, setTo, origin);
        if (y - 1 >= 0 && board[x][y - 1] == origin) solve(board, x, y - 1, setTo, origin);
        if (y + 1 < board[0].length && board[x][y + 1] == origin) solve(board, x, y + 1, setTo, origin);
    }

}
