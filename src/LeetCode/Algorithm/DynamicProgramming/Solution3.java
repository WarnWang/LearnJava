package LeetCode.Algorithm.DynamicProgramming;

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
}
