package LeetCode.Algorithm.Array;

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
        for (int i = 0; i < height; i++){
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

        for (int[] row: board)
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
}
