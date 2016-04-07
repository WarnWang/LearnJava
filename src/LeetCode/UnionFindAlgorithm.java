package LeetCode;

import java.util.Arrays;

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
        UnionFind numCount = new UnionFind(grid);
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
     * The following class is used to sole island number puzzles, detailed can be found in TagDFS.numIslands
     */
    private class UnionFind {
        private int _cols, _rows;
        private int _count = 0;

        private int[] _parent;

        UnionFind(char[][] grid) {
            _cols = grid[0].length;
            _rows = grid.length;
            _parent = new int[_cols * _rows];
            for (int i = 0; i < _rows; i++) {
                for (int j = 0; j < _cols; j++) {
                    if (grid[i][j] == '1') {
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
