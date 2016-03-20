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

    // https://leetcode.com/discuss/91467/my-7ms-java-solution-beats-99-55%25
    // TODO: understand this algorithm, Only 7ms needed
    public String longestPalindromeOnline(String src) {
        if (src.length() <= 1) {
            return src;
        }

        int[] p = new int[src.length() * 2 + 2];
        char[] str = new char[p.length];
        for (int i = 0, k = 0, j = src.length(); i < j; i++) {
            char c = src.charAt(i);
            str[++k] = '#';
            str[++k] = c;
        }
        str[str.length - 1] = '#';

        for (int i = 1, mx = 0, id = 0; i < str.length; i++) {
            if (mx > i) {
                p[i] = Math.min(p[2 * id - i], mx - i);
            } else {
                p[i] = 1;
            }
            for (; i + p[i] < str.length && str[i + p[i]] == str[i - p[i]]; p[i]++) ;
            if (p[i] + i > mx) {
                mx = p[i] + i;
                id = i;
            }
        }

        int maxIdx = 0;
        for (int i = 1; i < p.length; i++) {
            if (p[i] > p[maxIdx]) {
                maxIdx = i;
            }
        }
        int start = maxIdx - p[maxIdx] + 1;
        char[] chars = new char[p[maxIdx]];
        int j = 0;
        for (int i = start; i <= maxIdx; i++) {
            if (str[i] != '#') {
                chars[j++] = str[i];
            }
        }
        for (int i = maxIdx + 1, k = 2 * maxIdx - start; i <= k; i++) {
            if (str[i] != '#') {
                chars[j++] = str[i];
            }
        }
        return new String(chars, 0, j);
    }
}
