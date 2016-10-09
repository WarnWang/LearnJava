package LeetCode.WeeklyContest.Contest8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by warn on 9/10/2016.
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the
 * "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom
 * edges.
 * <p>
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or
 * lower.
 * <p>
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * <p>
 * Note:
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * Example:
 * <p>
 * Given the following 5x5 matrix:
 * <p>
 * Pacific ~   ~   ~   ~   ~
 * ~  1   2   2   3  (5) *
 * ~  3   2   3  (4) (4) *
 * ~  2   4  (5)  3   1  *
 * ~ (6) (7)  1   4   5  *
 * ~ (5)  1   1   2   4  *
 * *   *   *   * Atlantic
 * <p>
 * Return:
 * <p>
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificAtlanticWaterFlow {

    public List<int[]> pacificAtlantic(int[][] matrix) {
        ArrayList<int[]> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] canAchievePacific = new boolean[m][n];
        boolean[][] canAchieveAtlantic = new boolean[m][n];

        for (int i = 0; i < n; i++) {
            canAchieveAtlantic[m - 1][i] = true;
            canAchievePacific[0][i] = true;
        }

        for (int i = 0; i < m; i++) {
            canAchieveAtlantic[i][n - 1] = true;
            canAchievePacific[i][0] = true;
        }

        boolean changed = true;

        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        while (changed) {
            changed = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!canAchieveAtlantic[j][i]) {
                        for (int[] direction: directions) {
                            int newJ = j + direction[0];
                            int newI = i + direction[1];
                            if (newI < n && newI >= 0 && newJ < m && newJ >= 0) {
                                if (matrix[j][i] >= matrix[newJ][newI] && canAchieveAtlantic[newJ][newI]) {
                                    canAchieveAtlantic[j][i] = true;
                                    changed = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (!canAchievePacific[j][i]) {
                        for (int[] direction: directions) {
                            int newJ = j + direction[0];
                            int newI = i + direction[1];
                            if (newI < n && newI >= 0 && newJ < m && newJ >= 0) {
                                if (matrix[j][i] >= matrix[newJ][newI] && canAchievePacific[newJ][newI]) {
                                    canAchievePacific[j][i] = true;
                                    changed = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canAchieveAtlantic[i][j] && canAchievePacific[i][j]) result.add(new int[] {i, j});
            }
        }
        return result;
    }
}
