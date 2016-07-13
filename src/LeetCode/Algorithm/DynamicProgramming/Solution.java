package LeetCode.Algorithm.DynamicProgramming;

import sun.nio.cs.ext.MacHebrew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by warn on 30/5/2016.
 * Store some DP puzzles solution
 */
public class Solution {
    /**
     * Given a string, find the length of the longest substring without repeating characters.
     * <p>
     * Examples:
     * <p>
     * Given "abcabcbb", the answer is "abc", which the length is 3.
     * <p>
     * Given "bbbbb", the answer is "b", with the length of 1.
     * <p>
     * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
     * "pwke" is a subsequence and not a substring.
     *
     * @param s a string
     * @return the max length of substring.
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() < 2) return 1;
        int maxLength = 1;
        int[] substringLen = new int[s.length()];
        substringLen[0] = 1;
        char[] sArray = s.toCharArray();
        for (int i = 1, n = s.length(); i < n; i++) {
            char iC = sArray[i];
            if (iC == sArray[i - 1]) substringLen[i] = 1;
            else {
                int length = 2;
                for (int j = i - 2, bound = Math.max(0, i - substringLen[i - 1]); j >= bound; j--) {
                    if (iC == sArray[j]) break;
                    length++;
                }
                substringLen[i] = length;
                maxLength = Math.max(length, maxLength);
            }
        }
        return maxLength;
    }

    /**
     * ay you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one
     * and sell one share of the stock multiple times) with the following restrictions:
     * <p>
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     * Example:
     * <p>
     * prices = [1, 2, 3, 0, 2]
     * maxProfit = 3
     * transactions = [buy, sell, cooldown, buy, sell]
     *
     * @param prices an array of stock price
     * @return the max profit
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int n = prices.length;
        int[] finalOperationMatrix = new int[n];
        int[] profitList = new int[n];
        int maxProfit = 0;
        for (int i = 1; i < n; i++) {
            int profit = 0;
            for (int j = i - 1; j >= 0; j++) {
                if (j == 0) {
                    int currentProfit = prices[i] - prices[j];
                    if (currentProfit > profit) {
                        profit = currentProfit;
                        finalOperationMatrix[i] = 1;
                    }
                } else if (prices[i] > prices[j]) {
                    int currentProfit = prices[i] - prices[j];
                    if (finalOperationMatrix[j - 1] == 0) currentProfit += profitList[j - 1];
                    else if (j > 1) currentProfit += profitList[j - 2];
                    if (currentProfit > profit) {
                        profit = currentProfit;
                        finalOperationMatrix[i] = 1;
                    }
                } else if (profitList[j] > profit) {
                    profit = profitList[j];
                    finalOperationMatrix[i] = 0;
                } else if (prices[i] < prices[j]) break;

            }
            profitList[i] = profit;
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }

    /**
     * Given a string S and a string T, count the number of distinct subsequences of T in S.
     * <p>
     * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
     * of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a
     * subsequence of "ABCDE" while "AEC" is not).
     * <p>
     * Here is an example:
     * S = "rabbbit", T = "rabbit"
     * <p>
     * Return 3.
     *
     * @param s a new string
     * @param t another string
     * @return subsequence number
     */
    public int numDistinct(String s, String t) {
        if (t == null || t.length() == 0) return 1;
        if (s == null || s.length() < t.length()) return 0;
        int nT = t.length(), nS = s.length();
        int[][] count = new int[nS][nT];

        char sT = t.charAt(0);
        if (sT == s.charAt(0)) count[0][0] = 1;
        for (int i = 1; i < nS; i++) {
            char sC = s.charAt(i);
            if (sC == sT) count[i][0] = 1 + count[i - 1][0];
            else count[i][0] = count[i - 1][0];
        }

        for (int i = 1; i < nT; i++) {
            sT = t.charAt(i);
            for (int j = i; j < nS; j++) {
                char sC = s.charAt(j);
                if (sC == sT) count[j][i] = count[j][i - 1] + count[j - 1][i - 1];
                else count[j][i] = count[j - 1][i];
            }
        }
        for (int[] i : count) System.out.println(Arrays.toString(i));
        return count[nS - 1][nT - 1];
    }

    /**
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on
     * the row below.
     * <p>
     * For example, given the following triangle
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     *
     * @param triangle and list of triangle
     * @return the minimum path
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int minPath = 0;
        ArrayList<Integer> distance = new ArrayList<>(1);
        distance.add(triangle.get(0).get(0));
        minPath = distance.get(0);
        for (int i = 1, n = triangle.size(); i < n; i++) {
            minPath = Integer.MAX_VALUE;
            ArrayList<Integer> currentRow = new ArrayList<>(triangle.get(i));
            for (int j = 0, m = currentRow.size(); j < m; j++) {
                int formerDistance;
                if (j == 0) formerDistance = distance.get(0);
                else if (j == m - 1) formerDistance = distance.get(j - 1);
                else formerDistance = Math.min(distance.get(j - 1), distance.get(j));
                int currentDistance = formerDistance + currentRow.get(j);
                minPath = Math.min(currentDistance, minPath);
                currentRow.set(j, currentDistance);
            }
            distance = currentRow;
        }
        return minPath;
    }
}
