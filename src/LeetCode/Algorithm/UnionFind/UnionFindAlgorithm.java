package LeetCode.Algorithm.UnionFind;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by warn on 7/4/2016.
 * Use Union find algorithm to solve questions
 * About algorithm details https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
 */
public class UnionFindAlgorithm {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind numCount = new UnionFind(grid, '1');
        int[][] neighbors = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    for (int[] neighbor : neighbors) {
                        int x = i + neighbor[0], y = j + neighbor[1];
                        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] != '1') continue;
                        numCount.unionLocation(i * cols + j, x * cols + y);
                    }
                }
            }
        }
        return numCount.get_count();
    }

    /**
     * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     * For example,
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * After running your function, the board should be:
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     *
     * @param board a 2D board containing 'X' and '0'
     */
    public void solve(char[][] board) {
        if (board == null) return;
        int rows = board.length;
        if (rows <= 2) return;
        int cols = board[0].length;
        if (cols <= 2) return;

        UnionFind uf = new UnionFind(board, 'O');
        int[][] neighbors = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        HashSet<Integer> boardO = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O'){
                    if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) boardO.add(i * cols + j);
                    for (int[] neighbor: neighbors){
                        int x = neighbor[0] + i;
                        int y = neighbor[1] + j;
                        if (x < rows && x >= 0 && y < cols && y >= 0 && board[x][y] == 'O'){
                            uf.unionLocation(i * cols + j, x * cols + y);
                        }
                    }
                }
            }
        }

        HashSet<Integer> mergedBoardO = new HashSet<>();
        for (int i: boardO) {
            mergedBoardO.add(uf.getParent(i));
        }

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (board[i][j] == 'O') {
                    if (!mergedBoardO.contains(uf.getParent(i * cols + j))) board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * The following class is used to sole island number puzzles, detailed can be found in TagDFS.numIslands
     */
    private class UnionFind {
        private int _cols, _rows;
        private int _count = 0;

        private int[] _parent;

        UnionFind(char[][] grid, char target) {
            _cols = grid[0].length;
            _rows = grid.length;
            _parent = new int[_cols * _rows];
            for (int i = 0; i < _rows; i++) {
                for (int j = 0; j < _cols; j++) {
                    if (grid[i][j] == target) {
                        int id = i * _cols + j;
                        _parent[id] = id;
                        _count++;
                    }
                }
            }
        }

        void unionLocation(int id1, int id2) {
            int parent1 = _find(id1);
            int parent2 = _find(id2);
            if (parent1 != parent2) {
                _parent[parent1] = parent2;
                _count--;
            }
        }

        boolean isSameParent(int id1, int id2){
            return _find(id1) == _find(id2);
        }

        int getParent(int id){
            return _find(id);
        }

        private int _find(int id) {
            if (_parent[id] == id) return id;
            _parent[id] = _find(_parent[id]);
            return _parent[id];
        }

        int get_count() {
            return _count;
        }
    }
}
