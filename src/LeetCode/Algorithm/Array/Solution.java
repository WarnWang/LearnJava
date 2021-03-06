package LeetCode.Algorithm.Array;

import LeetCode.DataTypes.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by warn on 1/6/2016.
 * Store solution to Array puzzles
 */
public class Solution {

    /**
     * There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted
     * arrays. The overall run time complexity should be O(log (m+n)).
     *
     * @param nums1 one sorted array
     * @param nums2 another sorted array
     * @return the median of target array
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1, n2;
        if (nums1 == null) n1 = 0;
        else n1 = nums1.length;
        if (nums2 == null) n2 = 0;
        else n2 = nums2.length;
        return findMedianSortedArrays(nums1, 0, n1, nums2, 0, n2);
    }

    private double findMedianSortedArrays(int[] nums1, int nums1Start, int nums1End,
                                          int[] nums2, int nums2Start, int nums2End) {
        if (nums1Start >= nums1End && nums2Start >= nums2End) return 0;
        else if (nums1Start >= nums1End) {
            return findMedianSortedArrays(nums2, nums2Start, nums2End);
        } else if (nums2Start >= nums2End) return findMedianSortedArrays(nums1, nums1Start, nums1End);
        while (true) {
            if (nums1Start >= nums1End) return findMedianSortedArrays(nums2, nums2Start, nums2End);
            else if (nums2Start >= nums2End) return findMedianSortedArrays(nums1, nums1Start, nums1End);
            if (nums1End - nums1Start + nums2End - nums2Start == 2)
                return (double) (nums1[nums1Start] + nums2[nums2Start]) / 2.0;
            if (nums1[nums1Start] > nums2[nums2Start]) nums2Start++;
            else nums1Start++;
            if (nums1[nums1End - 1] > nums2[nums2End - 1]) nums1End--;
            else nums2End--;
        }

    }

    private double findMedianSortedArrays(int[] nums, int start, int end) {
        if (start >= end) return 0;
        else if ((end - start) % 2 == 1) return nums[(start + end) / 2];
        else return (double) (nums[(start + end) / 2] + nums[(start + end) / 2 - 1]) / 2.0;
    }

    /**
     * Given a sorted integer array without duplicates, return the summary of its ranges.
     * <p>
     * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
     *
     * @param nums a sorted integer array without duplicates
     * @return return the summary of its ranges
     */
    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> rangeList = new ArrayList<>();
        if (nums == null || nums.length == 0) return rangeList;
        int start = nums[0];
        boolean inRange = false;
        for (int i = 0, n = nums.length; i < n; i++) {
            if (inRange) {
                if (i + 1 == n || nums[i + 1] != nums[i] + 1) {
                    inRange = false;
                    rangeList.add(start + "->" + nums[i]);
                }
            } else {
                start = nums[i];
                if (i + 1 == n || nums[i + 1] != nums[i] + 1) {
                    rangeList.add(Integer.toString(nums[i]));
                } else inRange = true;
            }
        }
        return rangeList;
    }

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
     * @param numRows the list size
     * @return a list of the pascal triangle
     */
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> pascalTriangle = new ArrayList<>(numRows);
        if (numRows > 0)
            pascalTriangle.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = pascalTriangle.get(i - 1);
            ArrayList<Integer> newRow = new ArrayList<>(i + 1);
            newRow.add(1);
            for (int j = 1; j < i; j++) {
                newRow.add(lastRow.get(j - 1) + lastRow.get(j));
            }
            newRow.add(1);
            pascalTriangle.add(newRow);
        }
        return pascalTriangle;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one
     * and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the
     * same time (ie, you must sell the stock before you buy again).
     *
     * @param prices a price list
     * @return max profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        boolean isHold = false;
        int holdPrice = 0;
        int profit = 0;
        for (int i = 1, n = prices.length; i < n; i++) {
            int price = prices[i];
            int lastPrice = prices[i - 1];
            if (isHold) {
                if (price < lastPrice) {
                    profit += lastPrice - holdPrice;
                    isHold = false;
                }
            } else {
                if (price > lastPrice) {
                    isHold = true;
                    holdPrice = lastPrice;
                }
            }
        }
        if (isHold) {
            if (prices[prices.length - 1] > holdPrice)
                profit += prices[prices.length - 1] - holdPrice;
        }
        return profit;
    }

    /**
     * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
     * devised by the British mathematician John Horton Conway in 1970."
     * <p>
     * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with
     * its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above
     * Wikipedia article):
     * <p>
     * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     * Any live cell with two or three live neighbors lives on to the next generation.
     * Any live cell with more than three live neighbors dies, as if by over-population..
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * Write a function to compute the next state (after one update) of the board given its current state.
     * <p>
     * Follow up:
     * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update
     * some cells first and then use their updated values to update other cells.
     * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would
     * cause problems when the active area encroaches the border of the array. How would you address these problems?
     *
     * @param board a game board
     */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int height = board.length, width = board[0].length;
        if (width == 0) return;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int nearByLives = 0;
                if (i > 0) {
                    nearByLives += (board[i - 1][j] % 10);
                    if (j > 0) nearByLives += (board[i - 1][j - 1] % 10);
                    if (j < width - 1) nearByLives += board[i - 1][j + 1] % 10;
                }
                if (i < height - 1) {
                    nearByLives += (board[i + 1][j] % 10);
                    if (j > 0) nearByLives += (board[i + 1][j - 1] % 10);
                    if (j < width - 1) nearByLives += board[i + 1][j + 1] % 10;
                }
                if (j > 0) nearByLives += (board[i][j - 1] % 10);
                if (j < width - 1) nearByLives += board[i][j + 1] % 10;
                board[i][j] += 10 * nearByLives;
            }
        }

        for (int[] row : board)
            System.out.println(Arrays.toString(row));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int cell = board[i][j];
                if (cell % 10 == 0) {
                    if (cell == 30) board[i][j] = 1;
                    else board[i][j] = 0;
                } else {
                    if (cell < 20) board[i][j] = 0;
                    else if (cell < 40) board[i][j] = 1;
                    else board[i][j] = 0;
                }
            }
        }
    }

    // Suppose citations is already sorted
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        return hIndex(citations, 0, citations.length);
    }

    public int hIndex(int[] citations, int start, int end) {
        if (start >= end) return 0;
        else if (end - start == 1) {
            if (citations[start] >= citations.length - start) {
                return citations.length - start;
            } else return 0;
        }
        int middle = (start + end) / 2;
        if (citations[middle] >= citations.length - middle) {
            return Math.max(hIndex(citations, start, middle), citations.length - middle);
        } else {
            return hIndex(citations, middle + 1, end);
        }
    }

    /**
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     * <p>
     * Note:
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array, but it could be repeated more than once.
     *
     * @param nums an array nums
     * @return the duplicate number
     */
    public int findDuplicateOnn(int[] nums) {
        for (int i = 0, n = nums.length; i < n - 1; i++) {
            int num = nums[i];
            for (int j = i + 1; j < n; j++) {
                if (num == nums[j]) return num;
            }
        }
        return 0;
    }

    public int findDuplicateOnlogn(int[] nums) {
        int lowBound = 1;
        int upBound = nums.length - 1;
        int mid = (lowBound + upBound) / 2;
        while (lowBound <= upBound) {
            int smallNum = 0;
            int midNum = 0;
            for (int num : nums) {
                if (num == mid) midNum++;
                else if (num < mid) smallNum++;
            }
            if (midNum > 1) return mid;
            else if (smallNum > (mid - 1)) upBound = mid - 1;
            else lowBound = mid + 1;
            mid = (upBound + lowBound) / 2;
        }
        return lowBound;
    }

    /**
     * Given an unsorted integer array, find the first missing positive integer.
     * <p>
     * For example,
     * Given [1,2,0] return 3,
     * and [3,4,-1,1] return 2.
     * <p>
     * Your algorithm should run in O(n) time and uses constant space.
     * https://leetcode.com/problems/first-missing-positive/
     *
     * @param nums an unsorted integer array
     * @return the first missing positive integer
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        boolean[] isExisted = new boolean[nums.length];
        for (int num : nums) {
            if (num > 0 && num <= nums.length) isExisted[num - 1] = true;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!isExisted[i]) return i + 1;
        }
        return nums.length;
    }

    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * <p>
     * You may assume that the intervals were initially sorted according to their start times.
     * <p>
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     * <p>
     * Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     * <p>
     * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     *
     * @param intervals   a set of non-overlapping sorted intervals
     * @param newInterval new interval that need to be added in to interval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> newIntervals = new ArrayList<>();
        int oldIndex = 0;
        for (; oldIndex < intervals.size(); oldIndex++) {
            Interval interval = intervals.get(oldIndex);
            if (interval.end < newInterval.start) newIntervals.add(interval);
            else break;
        }
        if (oldIndex == intervals.size()) newIntervals.add(newInterval);
        else if (intervals.get(oldIndex).start > newInterval.end) {
            newIntervals.add(newInterval);
            for (; oldIndex < intervals.size(); oldIndex++) newIntervals.add(intervals.get(oldIndex));
        } else {
            int start = Math.min(newInterval.start, intervals.get(oldIndex).start);
            int end = newInterval.end;
            for (; oldIndex < intervals.size(); oldIndex++) {
                if (end >= intervals.get(oldIndex).start) end = Math.max(newInterval.end, intervals.get(oldIndex).end);
                else break;
            }
            newIntervals.add(new Interval(start, end));
            for (; oldIndex < intervals.size(); oldIndex++) newIntervals.add(intervals.get(oldIndex));
        }
        return newIntervals;
    }

    /**
     * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical
     * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
     * with x-axis forms a container, such that the container contains the most water.
     * <p>
     * Note: You may not slant the container.
     *
     * @param height a list of height
     * @return the max area.
     */
    public int maxArea(int[] height) {
        int maxAreaSize = 0;
        for (int i = 0, j = height.length - 1; i < j;){
            if (height[i] < height[j]) {
                int pre = height[i++];
                maxAreaSize = Math.max(maxAreaSize, pre * (j - i));

                while (j > i && height[i] <= pre) i++;
            } else {
                int pre = height[j--];
                maxAreaSize = Math.max(maxAreaSize, pre * (j - i));
                while (j > i && height[j] <= pre) j--;
            }
        }
        return maxAreaSize;
    }
}
