package LeetCode.Algorithm;

import LeetCode.DataTypes.TreeNode;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by warn on 15/3/2016.
 * Use to store some Puzzles with tag BFS
 */
public class TagBFS {
    /**
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
     * Given binary tree {3,9,20,#,#,15,7},
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
     *
     * @param root a root node of a tree
     * @return the zigzag level order list
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzagList = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        boolean reversed = true;
        if (root == null) return zigzagList;
        else {
            nodeQueue.add(root);
            levelQueue.add(0);
        }

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            int level = levelQueue.remove();
            if (zigzagList.size() == level) {
                zigzagList.add(new LinkedList<>());
                zigzagList.get(level).add(node.val);
                reversed = !reversed;
            } else {
                if (reversed) zigzagList.get(level).add(0, node.val);
                else zigzagList.get(level).add(node.val);
            }

            if (node.left != null) {
                nodeQueue.add(node.left);
                levelQueue.add(level + 1);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                levelQueue.add(level + 1);
            }
        }
        return zigzagList;
    }

    /**
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by
     * water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of
     * the grid are all surrounded by water.
     * There is also a DFS version
     *
     * @param grid 2d grid map
     * @return the number of lands
     */
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        int gridHeight = grid.length;
        if (gridHeight == 0) return 0;
        int gridWidth = grid[0].length;
        if (gridWidth == 0) return 0;
        int islandNum = 0;

        HashSet<Integer> exploredPosition = new HashSet<>();
        for (int i = 0; i < gridHeight * gridWidth; i++) {
            if (exploredPosition.contains(i)) continue;
            if (grid[i / gridWidth][i % gridWidth] == '1') {
                islandNum++;
                ArrayDeque<Integer> islandGrid = new ArrayDeque<>();
                islandGrid.add(i);
                while (!islandGrid.isEmpty()) {
                    int frontier = islandGrid.remove();
                    exploredPosition.add(frontier);
                    for (int k = 0; k < 4; k++) {
                        int nextI = frontier / gridWidth;
                        int nextJ = frontier % gridWidth;

                        switch (k) {
                            case 0:
                                nextI++;
                                if (nextI >= gridHeight) continue;
                                break;
                            case 1:
                                nextI--;
                                if (nextI < 0) continue;
                                break;
                            case 2:
                                nextJ++;
                                if (nextJ >= gridWidth) continue;
                                break;
                            default:
                                nextJ--;
                                if (nextJ < 0) continue;
                        }
                        if (!exploredPosition.contains(nextI * gridWidth + nextJ) && grid[nextI][nextJ] == '1'){
                            islandGrid.add(nextI * gridWidth + nextJ);
                        } else exploredPosition.add(nextI * gridWidth + nextJ);
                    }
                }
            }
        }
        return islandNum;
    }
}
