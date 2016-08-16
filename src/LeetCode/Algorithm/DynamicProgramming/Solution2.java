package LeetCode.Algorithm.DynamicProgramming;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by warn on 31/7/2016.
 * <p>
 * Store some DP puzzles solution
 */
public class Solution2 {
    /**
     * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations
     * that add up to a positive integer target.
     * <p>
     * Example:
     * <p>
     * nums = [1, 2, 3]
     * target = 4
     * <p>
     * The possible combination ways are:
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * <p>
     * Note that different sequences are counted as different combinations.
     * <p>
     * Therefore the output is 7.
     *
     * @param nums   an integer array with all positive numbers and no duplicates
     * @param target a positive integer target
     * @return the number of different combinations
     */
    public int combinationSum4(int[] nums, int target) {
        if (target <= 0) return 0;
        int[] combinationNum = new int[target + 1];
//        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num == i) combinationNum[i]++;
                else if (num < i) combinationNum[i] += combinationNum[i - num];
            }
        }
        return combinationNum[target];
    }

    /**
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence
     * of one or more dictionary words.
     * <p>
     * For example, given
     * s = "leetcode",
     * dict = ["leet", "code"].
     * <p>
     * Return true because "leetcode" can be segmented as "leet code".
     *
     * @param s        a string s
     * @param wordDict a dictionary of words dict
     * @return if s can be segmented into a space-separated sequence of one or more dictionary words
     */
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0) return true;
        else if (wordDict == null || wordDict.size() == 0) return false;
        int n = s.length();
        boolean[] isInDict = new boolean[n];
        int[] wordLength = new int[wordDict.size()];
        int index = 0;
        for (String str : wordDict) wordLength[index++] = str.length();
        for (int i = 0; i < n; i++) {
            if (wordDict.contains(s.substring(0, i + 1))) isInDict[i] = true;
            else {
                for (int j : wordLength) {
                    if (i - j < 0) continue;
                    if (isInDict[j - 1] && wordDict.contains(s.substring(i - j, i + 1))) {
                        isInDict[i] = true;
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(isInDict));
        return isInDict[n - 1];
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * <p>
     * Design an algorithm to find the maximum profit. You may complete at most k transactions.
     * <p>
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     *
     * @param k      maximum transaction times
     * @param prices stock price list
     * @return the max profit
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k <= 0) return 0;
        int n = prices.length;
        if (k >= n / 2) {
            int maxProfitNum = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) maxProfitNum += prices[i] - prices[i - 1];
            }
            return maxProfitNum;
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = -prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + localMax);
                localMax = Math.max(localMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }

    /**
     * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon
     * consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left
     * room and must fight his way through the dungeon to rescue the princess.
     * <p>
     * The knight has an initial health point represented by a positive integer. If at any point his health point drops
     * to 0 or below, he dies immediately.
     * <p>
     * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these
     * rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive
     * integers).
     * <p>
     * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward
     * in each step.
     * <p>
     * <p>
     * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
     * <p>
     * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the
     * optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
     * <p>
     * -2 (K)	-3	3
     * -5	-10	1
     * 10	30	-5 (P)
     *
     * @param dungeon a map
     * @return the minimal health needed
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int h = dungeon[0].length, w = dungeon.length;
        int maxRow = w - 1, maxCol = h - 1;
        int[][] minimalHealth = new int[w][h];
        minimalHealth[maxRow][maxCol] = Math.max(1 - dungeon[maxRow][maxCol], 1);
        for (int i = maxCol - 1; i >= 0; i--)
            minimalHealth[maxRow][i] = Math.max(minimalHealth[maxRow][i + 1] - dungeon[maxRow][i], 1);
        for (int i = maxRow - 1; i >= 0; i--)
            minimalHealth[i][maxCol] = Math.max(minimalHealth[i + 1][maxCol] - dungeon[i][maxCol], 1);

        for (int i = maxRow - 1; i >= 0; i--) {
            for (int j = maxCol - 1; j >= 0; j--) {
                minimalHealth[i][j] = Math.max(Math.min(minimalHealth[i + 1][j],
                        minimalHealth[i][j + 1]) - dungeon[i][j], 1);
            }
        }

        return Math.max(minimalHealth[0][0], 1);
    }

    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     * <p>
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * @param n climb time
     * @return climb times
     */
    public int climbStairs(int n) {
        if (n < 1) return 0;
        int[] climbTimes = new int[Math.max(n, 2)];
        climbTimes[0] = 1;
        climbTimes[1] = 2;

        for (int i = 2; i < n; i++)
            climbTimes[i] = climbTimes[i - 1] + climbTimes[i - 2];
        return climbTimes[n - 1];
    }
}
