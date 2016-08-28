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
            else if (c == ')' && left > 0){
                validLength[i] = validLength[i - 1] + 2;
                if (validLength[i] < i) validLength[i] += validLength[i - validLength[i]];
                left--;
                maxLength = Math.max(validLength[i], maxLength);
            }
        }
        return maxLength;
    }
}
