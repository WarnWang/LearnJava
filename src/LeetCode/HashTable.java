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
        int[] lengthOfEachPoint = new int[n];
        for (int i = 1; i < n - 1; i++) {
            int lastLength = endIndex - startIndex;
            if (((n - i) << 1) - 1 < lastLength) break;
            int begin = i - 1 - lengthOfEachPoint[i], end = i + 1 + lengthOfEachPoint[i];
            while (begin >= 0 && end < n && s.charAt(begin) == s.charAt(end)) {
                lengthOfEachPoint[i]++;
                begin--;
                end++;
            }
            int bound = lengthOfEachPoint[i] - 1;
            for (int j = 1; bound > 0; j++, bound--) {
                lengthOfEachPoint[j + i] = Integer.min(bound, lengthOfEachPoint[i - j]);
            }
            begin++;
            if (end - begin > endIndex - startIndex) {
                startIndex = begin;
                endIndex = end;
            }
        }
        lengthOfEachPoint = new int[n + 1];
        for (int i = ((endIndex - startIndex) >> 1); i < n - ((endIndex - startIndex + 1) >> 1); i++) {
            int begin = i - lengthOfEachPoint[i + 1], end = i + 1 + lengthOfEachPoint[i + 1];
            while (begin >= 0 && end < n && s.charAt(begin) == s.charAt(end)) {
                lengthOfEachPoint[i + 1]++;
                begin--;
                end++;
            }
            for (int j = 1, bound = lengthOfEachPoint[i + 1] - 1; bound > 0; bound--, j++) {
                lengthOfEachPoint[i + j + 1] = Integer.min(bound, lengthOfEachPoint[i - j + 1]);
            }
            begin++;
            if (end - begin > endIndex - startIndex) {
                startIndex = begin;
                endIndex = end;
            }
        }
        return s.substring(startIndex, endIndex);
    }

    // will TLE no better than my algorithm, not good
    public String longestPalindromeDynamicProgramming(String s) {
        if (s == null || s.length() <= 1) return s;
        int n = s.length();
        int[][] lengthMap = new int[n][n];
        int maxLength = 1;
        int maxStartIndex = 0;
        int maxEndIndex = 1;
        for (int length = 0; length < n; length++) {
            for (int startIndex = 0; startIndex < n - length; startIndex++) {
                int endIndex = startIndex + length;
                char startChar = s.charAt(startIndex);
                char endChar = s.charAt(endIndex);
                if (startChar == endChar) {
                    if (length > 1 && lengthMap[startIndex + 1][endIndex - 1] != length - 1) continue;
                    if (endIndex == startIndex) lengthMap[startIndex][endIndex] = 1;
                    else if (endIndex - 1 == startIndex) lengthMap[startIndex][endIndex] = 2;
                    else {
                        lengthMap[startIndex][endIndex] = lengthMap[startIndex + 1][endIndex - 1] + 2;
                        if (length + 1 > maxLength) {
                            maxLength = length;
                            maxStartIndex = startIndex;
                            maxEndIndex = endIndex;
                        }
                    }
                }
            }
        }
        return s.substring(maxStartIndex, maxEndIndex);
    }

    public String longestPalindromeManacher(String s) {
        if (s == null || s.length() < 2) return s;
        int n = s.length();
        int palindromeLength[] = new int[2 * n + 1];
        int maxLength = 1;
        int maxLengthStartIndex = 0;
        int currentBound = 0;
        int currentIndex = 0;
        char[] chars = s.toCharArray();
        for (int i = 1, nI = palindromeLength.length; i < nI - maxLength; i++) {
            int index = (i >> 1);
            if (currentBound < 1) {
                palindromeLength[i] = (i & 1);
            } else {
                palindromeLength[i] = Integer.min(currentBound, palindromeLength[currentIndex]);
            }
            for (int j = (i & 1) + (palindromeLength[i] >> 1),
                 upperBound = Integer.min(n - index - 1, index - ((i + 1) & 1)); j <= upperBound; j++) {
                int lowerIndex = index - j - ((i + 1) & 1);
                if (chars[lowerIndex] == chars[index + j]) palindromeLength[i] += 2;
                else break;
            }
            if (palindromeLength[i] > maxLength) {
                maxLength = palindromeLength[i];
                maxLengthStartIndex = (i - palindromeLength[i]) >> 1;
            }
            if (currentBound < palindromeLength[i]) {
                currentBound = palindromeLength[i];
                currentIndex = i;
            }
            currentBound--;
            currentIndex--;
        }
        return s.substring(maxLengthStartIndex, maxLengthStartIndex + maxLength);
    }

    // https://leetcode.com/discuss/91467/my-7ms-java-solution-beats-99-55%25
    // description can be find in http://articles.leetcode.com/longest-palindromic-substring-part-ii
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
