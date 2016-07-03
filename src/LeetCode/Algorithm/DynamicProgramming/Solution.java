package LeetCode.Algorithm.DynamicProgramming;

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
}
