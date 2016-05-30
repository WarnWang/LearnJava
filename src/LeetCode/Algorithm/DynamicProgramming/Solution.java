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
}
