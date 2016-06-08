package LeetCode.Algorithm.String;

import java.util.Stack;

/**
 * Created by warn on 8/6/2016.
 * Solution to puzzles with tag string
 */
public class Solution {
    /**
     * Given an input string, reverse the string word by word.
     * <p>
     * For example,
     * Given s = "the sky is blue",
     * return "blue is sky the".
     * <p>
     * Update (2015-02-12):
     * For C programmers: Try to solve it in-place in O(1) space.
     * <p>
     * click to show clarification.
     * <p>
     * Clarification:
     * What constitutes a word?
     * A sequence of non-space characters constitutes a word.
     * Could the input string contain leading or trailing spaces?
     * Yes. However, your reversed string should not contain leading or trailing spaces.
     * How about multiple spaces between two words?
     * Reduce them to a single space in the reversed string.
     */
    public String reverseWordsStack(String s) {
        if (s == null || s.length() == 0) return "";
        Stack<String> wordStack = new Stack<>();
        int lastNonSpace = -1;
        for (int i = 0, n = s.length(); i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ' && lastNonSpace != -1) {
                wordStack.push(s.substring(lastNonSpace, i));
                lastNonSpace = -1;
            } else if (c != ' ' && lastNonSpace == -1) lastNonSpace = i;
        }
        if (lastNonSpace != -1) wordStack.push(s.substring(lastNonSpace));
        if (wordStack.isEmpty()) return "";
        StringBuilder reverseString = new StringBuilder();
        while (!wordStack.isEmpty()) {
            reverseString.append(wordStack.pop());
            reverseString.append(' ');
        }
        reverseString.deleteCharAt(reverseString.length() - 1);
        return reverseString.toString();
    }

    public static void main(String args[]) {
        Solution test = new Solution();
        System.out.println(test.reverseWordsStack(" a") + "!");
    }
}
