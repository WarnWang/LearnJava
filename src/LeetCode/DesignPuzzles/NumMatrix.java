package LeetCode.DesignPuzzles;

/**
 * Created by warn on 11/6/2016.
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1,
 * col1) and lower right corner (row2, col2).
 * <p>
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which
 * contains sum = 8.
 * <p>
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 */
public class NumMatrix {
    private int[][] prefixSum;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            prefixSum = null;
        } else {
            prefixSum = new int[matrix.length][matrix[0].length];
            prefixSum[0][0] = matrix[0][0];
            for (int i = 1; i < matrix.length; i++) {
                prefixSum[i][0] = matrix[i][0] + prefixSum[i - 1][0];
            }
            for (int i = 1; i < matrix[0].length; i++) {
                prefixSum[0][i] = matrix[0][i] + prefixSum[0][i - 1];
            }
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + matrix[i][j];
                }
            }
        }
    }

    public static void main(String args[]) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix test = new NumMatrix(matrix);
        System.out.println(test.sumRegion(2, 1, 4, 3));
        System.out.println(test.sumRegion(1, 2, 2, 4));
        System.out.println(test.sumRegion(1, 1, 2, 2));
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (prefixSum == null) return 0;
        if (row1 == 0 && col1 == 0) return prefixSum[row2][col2];
        else if (row1 == 0) return prefixSum[row2][col2] - prefixSum[row2][col1 - 1];
        else if (col1 == 0) return prefixSum[row2][col2] - prefixSum[row1 - 1][col2];
        else
            return prefixSum[row2][col2] + prefixSum[row1 - 1][col1 - 1] - prefixSum[row2][col1 - 1]
                    - prefixSum[row1 - 1][col2];
    }
}