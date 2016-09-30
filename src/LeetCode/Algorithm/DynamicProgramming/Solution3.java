package LeetCode.Algorithm.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by warn on 28/8/2016.
 * Store some DP puzzles solution
 */
public class Solution3 {
    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
     * parentheses substring.
     * <p>
     * For "(()", the longest valid parentheses substring is "()", which has length = 2.
     * <p>
     * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     *
     * @param s a string containing just the characters '(' and ')'
     * @return the length of the longest valid (well-formed) parentheses substring
     */
    public int longestValidParenthesesTLE(String s) {
        if (s == null || s.length() < 2) return 0;
        int n = s.length();
        int maxLength = 0;
        boolean[][] isValidParentheses = new boolean[n][n];
        for (int len = 1; len < n; len += 2) {
            for (int i = 0; i < n - len; i++) {
                int end = i + len;
                char endChar = s.charAt(end);
                char startChar = s.charAt(i);
                if (endChar == '(' || startChar == ')') continue;
                if (len == 1) {
                    if (endChar == ')' && startChar == '(') isValidParentheses[i][end] = true;
                } else if (isValidParentheses[i + 1][end - 1] && endChar == ')' && startChar == '(')
                    isValidParentheses[i][end] = true;
                else {
                    for (int j = i + 1; j < end; j += 2) {
                        if (isValidParentheses[i][j] && isValidParentheses[j + 1][end]) {
                            isValidParentheses[i][end] = true;
                            break;
                        }
                    }
                }
                if (isValidParentheses[i][end]) maxLength = Math.max(maxLength, len + 1);
            }
        }
        return maxLength;
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        int n = s.length();
        int maxLength = 0;
        int[] validLength = new int[n];
        int left = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') left++;
            else if (c == ')' && left > 0) {
                validLength[i] = validLength[i - 1] + 2;
                if (validLength[i] < i) validLength[i] += validLength[i - validLength[i]];
                left--;
                maxLength = Math.max(validLength[i], maxLength);
            }
        }
        return maxLength;
    }

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * <p>
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * <p>
     * For example, given s = "aab",
     * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
     *
     * @param s a string s
     * @return the minimal partition num
     */
    public int minCut(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] chars = s.toCharArray();
        int n = chars.length;
        boolean[][] isPalindrome = new boolean[n][n];
        int[] minCut = new int[n];
        for (int i = 1; i < n; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (chars[j] == chars[i]) {
                    if (j > i - 2 || isPalindrome[j + 1][i - 1]) {
                        isPalindrome[j][i] = true;
                        if (j == 0) min = 0;
                        else min = Math.min(min, minCut[j - 1] + 1);
                    }
                }
            }
            minCut[i] = min;
        }
        return minCut[n - 1];
    }

    /**
     * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in
     * this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
     * <p>
     * If there are multiple solutions, return any subset is fine.
     * <p>
     * Example 1:
     * <p>
     * nums: [1,2,3]
     * <p>
     * Result: [1,2] (of course, [1,3] will also be ok)
     * Example 2:
     * <p>
     * nums: [1,2,4,8]
     * <p>
     * Result: [1,2,4,8]
     *
     * @param nums a list of number
     * @return the largest divisible subset
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int n = nums.length;
        Arrays.sort(nums);
        int[] count = new int[n], parent = new int[n];
        int maxIdx = 0;
        count[0] = 1;
        parent[0] = -1;
        for (int i = 1; i < n; i++) {
            parent[i] = -1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && count[j] + 1 > count[i]) {
                    parent[i] = j;
                    count[i] = count[j] + 1;
                }
            }
            if (count[maxIdx] < count[i]) maxIdx = i;
        }

        for (; maxIdx != -1; maxIdx = parent[maxIdx]) {
            result.add(nums[maxIdx]);
        }
        return result;
    }

    /**
     * Given a string s and a string t, check if s is subsequence of t.
     * <p>
     * You may assume that there is only lower case English letters in both s and t. t is potentially a very long
     * (length ~= 500,000) string, and s is a short string (<=100).
     * <p>
     * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
     * of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a
     * subsequence of "abcde" while "aec" is not).
     * <p>
     * Example 1:
     * s = "abc", t = "ahbgdc"
     * <p>
     * Return true.
     * <p>
     * Example 2:
     * s = "axc", t = "ahbgdc"
     * <p>
     * Return false.
     * <p>
     * Follow up:
     * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if
     * T has its subsequence. In this scenario, how would you change your code?
     *
     * @param s one string
     * @param t another string
     * @return if s is subsequence of t or not
     */
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) return true;
        if (t == null || t.length() == 0) return false;
        char[] sArray = s.toCharArray(), tArray = t.toCharArray();
        for (int i = 0, nS = sArray.length, nT = tArray.length, j = 0; i < nT; i++) {
            if (tArray[i] == sArray[j]) j++;
            if (j == nS) return true;
        }
        return false;
    }

    /**
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each
     * operation is counted as 1 step.)
     * <p>
     * You have the following 3 operations permitted on a word:
     * <p>
     * a) Insert a character
     * b) Delete a character
     * c) Replace a character
     *
     * @param word1 word 1
     * @param word2 word 2
     * @return the minimum number of steps required to convert word1 to word2
     */
    public int minDistance(String word1, String word2) {
        int n1, n2;
        if (word1 == null) n1 = 0;
        else n1 = word1.length();
        if (word2 == null) n2 = 0;
        else n2 = word2.length();
        if (n1 == 0) return n2;
        else if (n2 == 0) return n1;

        minimalDistanceInfo = new int[n1][n2];

        minDistance(word1, word2, 0, 0, n1, n2);

        return minimalDistanceInfo[0][0];
    }

    private int[][] minimalDistanceInfo;

    private int minDistance(String word1, String word2, int index1, int index2, int n1, int n2) {

        if (index1 == n1) return n2 - index2;
        else if (index2 == n2) return n1 - index1;
        else if (minimalDistanceInfo[index1][index2] > 0) return minimalDistanceInfo[index1][index2];
        else if (word1.charAt(index1) == word2.charAt(index2))
            minimalDistanceInfo[index1][index2] = minDistance(word1, word2, index1 + 1, index2 + 1, n1, n2);
        else {
            int minimalDistance = 1 + minDistance(word1, word2, index1 + 1, index2, n1, n2);
            minimalDistance = Math.min(minimalDistance, 1 + minDistance(word1, word2, index1 + 1, index2 + 1, n1, n2));
            minimalDistance = Math.min(minimalDistance, 1 + minDistance(word1, word2, index1, index2 + 1, n1, n2));
            minimalDistanceInfo[index1][index2] = minimalDistance;
        }
        return minimalDistanceInfo[index1][index2];
    }
}
