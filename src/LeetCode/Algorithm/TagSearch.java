package LeetCode.Algorithm;

import java.util.Arrays;

/**
 * Created by warn on 30/4/2016.
 * Store puzzles with tag sort
 */
public class TagSearch {

    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * <p>
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * For example,
     * <p>
     * Consider the following matrix:
     * <p>
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true.
     * <p>
     * Given target = 20, return false.
     *
     * @param matrix 2-D array represent a matrix
     * @param target the target value
     * @return whether this value is in this matrix or not
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int height = matrix.length;
        int width = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[height - 1][width - 1]) return false;
        for (int i = 0; i < height; i++) {
            if (matrix[i][width-1] < target) continue;
            if (matrix[i][0] > target) break;
            if (Arrays.binarySearch(matrix[i], target) >= 0){
                return true;
            }
        }
        return false;
    }
}
