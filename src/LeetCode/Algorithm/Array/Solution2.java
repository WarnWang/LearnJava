package LeetCode.Algorithm.Array;

import LeetCode.DataTypes.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    /**
     * Given a collection of intervals, merge all overlapping intervals.
     * <p>
     * For example,
     * Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     *
     * @param intervals a collection of intervals
     * @return merge all overlapping intervals
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> merged = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return merged;
        int n = intervals.size();
        int[] lowBound = new int[n], upBound = new int[n];
        for (int i = 0; i < n; i++) {
            Interval interval = intervals.get(i);
            lowBound[i] = interval.start;
            upBound[i] = interval.end;
        }
        Arrays.sort(lowBound);
        Arrays.sort(upBound);
        merged.add(new Interval(lowBound[0], upBound[0]));

        for (int i = 1, index = 0; i < n; i++) {
            Interval lastInterval = merged.get(index);
            if (lastInterval.end >= lowBound[i]) merged.get(index).end = upBound[i];
            else {
                merged.add(new Interval(lowBound[i], upBound[i]));
                index++;
            }
        }
        return merged;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * <p>
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     *
     * @param prices an array for which the ith element is the price of a given stock on day i
     * @return the maximum profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int n = prices.length;
        int[] formerMax = new int[n], laterMax = new int[n];
        int first = prices[0], last = prices[n - 1];
        for (int i = 1; i < n; i++) {
            int current = prices[i];
            formerMax[i] = Math.max(formerMax[i - 1], current - first);
            if (current < first) first = current;
            current = prices[n - i - 1];
            laterMax[n - i - 1] = Math.max(laterMax[n - i], last - current);
            if (last < current) last = current;
        }
        System.out.println(Arrays.toString(formerMax));
        System.out.println(Arrays.toString(laterMax));
        int maximum = laterMax[0];
        for (int i = 0; i < n; i++) {
            maximum = Math.max(formerMax[i] + laterMax[i], maximum);
        }
        return maximum;
    }

    public int maxProfitNoExtraMemory(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for (int i : prices) {                              // Assume we only have 0 money at first
            release2 = Math.max(release2, hold2 + i);     // The maximum if we've just sold 2nd stock so far.
            hold2 = Math.max(hold2, release1 - i);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1 + i);     // The maximum if we've just sold 1nd stock so far.
            hold1 = Math.max(hold1, -i);          // The maximum if we've just buy  1st stock so far.
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }

    /**
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new
     * length.
     * <p>
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * <p>
     * For example,
     * Given input array nums = [1,1,2],
     * <p>
     * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It
     * doesn't matter what you leave beyond the new length.
     *
     * @param nums a sorted array
     * @return the element number in new array
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[n] = nums[i];
                n++;
            }
        }
        return n;
    }

    /**
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * <p>
     * For example,
     * Given n = 3,
     * <p>
     * You should return the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     *
     * @param n the length of then new matrix
     * @return a spiral matrix
     */
    public int[][] generateMatrix(int n) {
        if (n <= 0) return new int[0][0];

        int[][] spiralMatrix = new int[n][n];

        int[][] directionMatrix = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int i = 0, m = n * n, x = 0, y = 0, direction = 0; i < m; i++) {
            spiralMatrix[x][y] = i + 1;

            int nextX = x + directionMatrix[direction][0];
            int nextY = y + directionMatrix[direction][1];
            if (nextX == n || nextX < 0 || nextY == n || nextY < 0 || spiralMatrix[nextX][nextY] > 0) {
                direction++;
                if (direction == 4) direction = 0;
                x += directionMatrix[direction][0];
                y += directionMatrix[direction][1];
            } else {
                x = nextX;
                y = nextY;
            }
        }
        return spiralMatrix;
    }

    /**
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to
     * a specific target number.
     * <p>
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1
     * must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
     * <p>
     * You may assume that each input would have exactly one solution.
     * <p>
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     *
     * @param numbers an array of integers
     * @param target target value
     * @return result index
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        for (int i = 0, n = numbers.length; i < n; i++) {
            result[0] = i + 1;
            int resultIndex = Arrays.binarySearch(numbers, i + 1, n, target - numbers[i]);
            if (resultIndex > 0) {
                result[1] = resultIndex + 1;
                break;
            }
        }
        return result;
    }

    /**
     * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between
     * any two consecutive elements is the same.
     * <p>
     * For example, these are arithmetic sequence:
     * <p>
     * 1, 3, 5, 7, 9
     * 7, 7, 7, 7
     * 3, -1, -5, -9
     * The following sequence is not arithmetic.
     * <p>
     * 1, 1, 2, 5, 7
     * <p>
     * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q)
     * such that 0 <= P < Q < N.
     * <p>
     * A slice (P, Q) of array A is called arithmetic if the sequence:
     * A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
     * <p>
     * The function should return the number of arithmetic slices in the array A.
     * <p>
     * <p>
     * Example:
     * <p>
     * A = [1, 2, 3, 4]
     * <p>
     * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
     *
     * @param A A zero-indexed array A consisting of N numbers
     * @return the number of sub sequence that is arithmetic slice
     */
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) return 0;

        int sum = 0, count = 0;
        for (int i = 1, n = A.length - 1; i < n; i++) {
            if (A[i] - A[i - 1] == A[i + 1] - A[i]) count++;
            else {
                sum += count * (count + 1) / 2;
                count = 0;
            }
        }
        sum += count * (count + 1) / 2;
        return sum;
    }
}
