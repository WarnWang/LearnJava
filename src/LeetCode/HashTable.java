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
        int startIndex = 0;
        int endIndex = 1;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int minLength = i + endIndex - startIndex;
            for (int j = n; j > minLength; j--) {
                if (isPalindromic(s, i, j)) {
                    startIndex = i;
                    endIndex = j;
                    break;
                }
            }
        }
        return s.substring(startIndex, endIndex);
    }

    public boolean isPalindromic(String s, int startIndex, int endIndex) {
        endIndex--;
        for (; startIndex < endIndex; startIndex++, endIndex--) {
            if (s.charAt(startIndex) != s.charAt(endIndex)) return false;
        }
        return true;
    }
}
