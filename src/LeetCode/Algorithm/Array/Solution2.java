package LeetCode.Algorithm.Array;

import java.util.*;

/**
 * Created by warn on 9/8/2016.
 * Store solution to Array puzzles
 */
public class Solution2 {
    /**
     * Given numRows, generate the first numRows of Pascal's triangle.
     * <p>
     * For example, given numRows = 5,
     * Return
     * <p>
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]
     *
     * @param numRows the row number needed
     * @return the result array list
     */
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> pascalTriangle = new ArrayList<>();
        if (numRows <= 0) return pascalTriangle;
        pascalTriangle.add(Collections.singletonList(1));
        for (int i = 2; i <= numRows; i++) {
            Integer[] row = new Integer[i];
            List<Integer> lastRow = pascalTriangle.get(i - 2);
            row[0] = 1;
            for (int j = 1; j < i - 1; j++) {
                row[j] = lastRow.get(j - 1) + lastRow.get(j);
            }
            row[i - 1] = 1;
            pascalTriangle.add(Arrays.asList(row));
        }
        return pascalTriangle;
    }

    /**
     * Given an index k, return the kth row of the Pascal's triangle.
     * <p>
     * For example, given k = 3,
     * Return [1,3,3,1].
     *
     * @param rowIndex the kth row
     * @return the kth
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex <= 0) return Collections.emptyList();
        Integer[] row = new Integer[rowIndex + 1];
        row[0] = 1;
        for (int i = 0; i <= rowIndex; i++) {
            int middle = i / 2;
            for (int j = middle; j > 0; j--) {
                row[j] += row[j - 1];
            }
            for (int j = middle + 1; j <= i; j++) {
                row[j] = row[i - j];
            }
        }
        return Arrays.asList(row);
    }

    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * <p>
     * Each element in the array represents your maximum jump length at that position.
     * <p>
     * Determine if you are able to reach the last index.
     * <p>
     * For example:
     * A = [2,3,1,1,4], return true.
     * <p>
     * A = [3,2,1,0,4], return false.
     *
     * @param nums an array of non-negative integers represent maximum jump length
     * @return whether you can reach the last index or not.
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length < 2) return true;
        int n = nums.length;
        int minimal = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int jumpSteps = nums[i];
            if (jumpSteps + i >= minimal) minimal = i;
        }
        return minimal == 0;
    }
}
