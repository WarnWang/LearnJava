package LeetCode;

/**
 * Created by warn on 20/3/2016.
 * Use to store the problems with hash table
 */
public class HashTable {
    /**
     * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is
     * 1000, and there exists one unique longest palindromic substring.
     *
     * @param s a string S
     * @return the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null) return null;
        int n = s.length();
        if (n == 0) return s;
        int startIndex = 0;
        int endIndex = 1;
        for (int i = 1; i < n - 1; i++) {
            int lastLength = endIndex - startIndex;
            if (((n - i) << 1) - 1 < lastLength) break;
            int begin = i - 1, end = i + 1;
            while (begin >= 0 && end < n && s.charAt(begin) == s.charAt(end)) {
                begin--;
                end++;
            }
            begin++;
            if (end - begin > endIndex - startIndex) {
                startIndex = begin;
                endIndex = end;
            }
        }
        for (int i = (endIndex - startIndex) >> 1; i < n - ((endIndex - startIndex + 1) >> 1); i++) {
            int begin = i, end = i + 1;
            while (begin >= 0 && end < n && s.charAt(begin) == s.charAt(end)) {
                begin--;
                end++;
            }
            begin++;
            if (end - begin > endIndex - startIndex) {
                startIndex = begin;
                endIndex = end;
            }
        }
        return s.substring(startIndex, endIndex);
    }
}
