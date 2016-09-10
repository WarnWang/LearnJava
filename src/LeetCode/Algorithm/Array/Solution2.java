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
}
