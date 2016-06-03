package LeetCode.Algorithm.DepthFirstSearch;

import LeetCode.DataTypes.TreeNode;

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
            if (board[i][0] == 'O') {
                board[i][0] = '1';
                solve(board, i, 0, '1', 'O');
            }
            if (board[i][cols - 1] == 'O') {
                board[i][cols - 1] = '1';
                solve(board, i, cols - 1, '1', 'O');
            }
        }

        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = '1';
                solve(board, 0, i, '1', 'O');
            }
            if (board[rows - 1][i] == 'O') {
                board[rows - 1][i] = '1';
                solve(board, rows - 1, i, '1', 'O');
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '1') board[i][j] = 'O';
            }
        }
    }

    private void solve(char[][] board, int x, int y, char setTo, char origin) {
        if (x + 1 < board.length - 1 && board[x + 1][y] == origin) {
            board[x + 1][y] = setTo;
            solve(board, x + 1, y, setTo, origin);
        }
        if (x - 1 > 0 && board[x - 1][y] == origin) {
            board[x - 1][y] = setTo;
            solve(board, x - 1, y, setTo, origin);
        }
        if (y - 1 > 0 && board[x][y - 1] == origin) {
            board[x][y - 1] = setTo;
            solve(board, x, y - 1, setTo, origin);
        }
        if (y + 1 < board[0].length - 1 && board[x][y + 1] == origin) {
            board[x][y + 1] = setTo;
            solve(board, x, y + 1, setTo, origin);
        }
    }

    /**
     * There are a total of n courses you have to take, labeled from 0 to n - 1.
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
     * expressed as a pair: [0,1]
     * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should
     * take to finish all courses.
     * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all
     * courses, return an empty array.
     * <p>
     * For example:
     * 2, [[1,0]]
     * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct
     * course order is [0,1]
     * 4, [[1,0],[2,0],[3,1],[3,2]]
     * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both
     * courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another
     * correct ordering is[0,2,1,3].
     * <p>
     * Note:
     * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
     *
     * @param numCourses    Course number
     * @param prerequisites the prerequisite course of one course
     * @return the course taken order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[]{};
        int[] courseSchedule = new int[numCourses];
        if (prerequisites == null || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                courseSchedule[i] = i;
            }
            return courseSchedule;
        }

        // key is the prerequisites course, and the target is
        boolean[] isPrerequisite = new boolean[numCourses];
        boolean[] hasPrerequisite = new boolean[numCourses];
        HashMap<Integer, ArrayList<Integer>> prerequisiteMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            isPrerequisite[prerequisite[0]] = true;
            hasPrerequisite[prerequisite[1]] = true;
            if (prerequisiteMap.containsKey(prerequisite[1])) prerequisiteMap.get(prerequisite[1]).add(prerequisite[0]);
            else prerequisiteMap.put(prerequisite[1], new ArrayList<>(Collections.singletonList(prerequisite[0])));
        }

        if (prerequisiteMap.size() == numCourses) return courseSchedule;

        int courseIndex = 0;
        for (int i = 0; i < numCourses; i++) {
            if (!hasPrerequisite[i]) {
                courseSchedule[courseIndex++] = i;
                isPrerequisite[i] = false;
            }
        }

        while (!prerequisiteMap.isEmpty()) {
            ArrayList<Integer> courseTaken = new ArrayList<>();
            for (int course : prerequisiteMap.keySet()) {
                boolean canTake = true;
                for (int prerequisite : prerequisiteMap.get(course)) {
                    if (isPrerequisite[prerequisite]) {
                        canTake = false;
                        break;
                    }
                }
                if (canTake) {
                    courseSchedule[courseIndex++] = course;
                    isPrerequisite[course] = false;
                    courseTaken.add(course);
                }
            }
            if (courseIndex == numCourses) break;
            if (courseTaken.isEmpty()) return new int[]{};
            else {
                for (int course : courseTaken) prerequisiteMap.remove(course);
            }
        }
        return courseSchedule;
    }

    public int[] findOrderImprove(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[]{};
        int[] courseSchedule = new int[numCourses];
        if (prerequisites == null || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                courseSchedule[i] = i;
            }
            return courseSchedule;
        }

        boolean[] hasPrerequisites = new boolean[numCourses];
        boolean[] isTaken = new boolean[numCourses];
        HashMap<Integer, ArrayList<Integer>> prerequisiteMap = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> courseMap = new HashMap<>();

        for (int[] coursePair : prerequisites) {
            hasPrerequisites[coursePair[0]] = true;
            if (prerequisiteMap.containsKey(coursePair[1])) prerequisiteMap.get(coursePair[1]).add(coursePair[0]);
            else prerequisiteMap.put(coursePair[1], new ArrayList<>(Collections.singletonList(coursePair[0])));

            if (courseMap.containsKey(coursePair[0])) courseMap.get(coursePair[0]).add(coursePair[1]);
            else courseMap.put(coursePair[0], new ArrayList<>(Collections.singletonList(coursePair[1])));
        }

        int courseIndex = 0;
        Stack<Integer> courseToExplore = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!hasPrerequisites[i]) {
                courseSchedule[courseIndex++] = i;
                isTaken[i] = true;
                courseToExplore.push(i);
            }
        }

        while (!courseToExplore.isEmpty()) {
            int frontier = courseToExplore.pop();
            if (isTaken[frontier]) {
                if (prerequisiteMap.containsKey(frontier))
                    courseToExplore.addAll(prerequisiteMap.get(frontier));
            } else {
                if (!courseMap.containsKey(frontier)) {
                    isTaken[frontier] = true;
                    courseSchedule[courseIndex++] = frontier;
                    if (prerequisiteMap.containsKey(frontier)) courseToExplore.addAll(prerequisiteMap.get(frontier));
                } else {
                    boolean canTake = true;
                    for (int i : courseMap.get(frontier)) {
                        if (!isTaken[i]) {
                            canTake = false;
                            break;
                        }
                    }
                    if (canTake) {
                        isTaken[frontier] = true;
                        courseSchedule[courseIndex++] = frontier;
                        if (prerequisiteMap.containsKey(frontier))
                            courseToExplore.addAll(prerequisiteMap.get(frontier));
                    }
                }
            }
            if (courseIndex == numCourses) return courseSchedule;
        }
        if (courseIndex == numCourses) return courseSchedule;
        else return new int[]{};
    }

    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * <p>
     * For example:
     * Given binary tree {1,#,2,3},
     * 1
     *  \
     *   2
     *  /
     * 3
     * return [1,3,2].
     *
     * @param root a binary tree
     * @return the inorder traversal of its nodes' values
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        HashSet<TreeNode> traveledNode = new HashSet<>();
        ArrayList<Integer> inorderTraversal = new ArrayList<>();
        Stack<TreeNode> frontier = new Stack<>();
        frontier.push(root);
        while (!frontier.isEmpty()) {
            TreeNode currentNode = frontier.peek();
            if (currentNode.left == null || traveledNode.contains(currentNode.left)) {
                inorderTraversal.add(currentNode.val);
                traveledNode.add(currentNode);
                frontier.pop();
                if (currentNode.right != null) frontier.push(currentNode.right);
            } else if (currentNode.left != null && !traveledNode.contains(currentNode.left)) {
                frontier.push(currentNode.left);
            }
        }
        return inorderTraversal;
    }
}