package LeetCode;

import java.util.*;

/**
 * Created by warn on 20/3/2016.
 * Use to store the problems with hash table
 */
public class TagHashTable {
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

    /**
     * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the
     * concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
     *
     * @param words a list of unique words
     * @return all pairs of distinct indices
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null) return null;
        int n = words.length;
        List<List<Integer>> palindromePairList = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int iLength = words[i].length();
                int jLength = words[j].length();
                String temp = words[i] + words[j];
                int startIndex = 0;
                int endIndex = iLength + jLength - 1;
                while (startIndex < endIndex) {
                    if (temp.charAt(startIndex) != temp.charAt(endIndex)) break;
                    startIndex++;
                    endIndex--;
                }
                if (iLength == jLength) {
                    if (startIndex >= endIndex) {
                        palindromePairList.add(Arrays.asList(i, j));
                        palindromePairList.add(Arrays.asList(j, i));
                    }
                } else {
                    if (startIndex >= endIndex) palindromePairList.add(Arrays.asList(i, j));
                    else {
                        temp = words[j] + words[i];
                        startIndex = 0;
                        endIndex = iLength + jLength - 1;
                        while (startIndex < endIndex) {
                            if (temp.charAt(startIndex) != temp.charAt(endIndex)) break;
                            startIndex++;
                            endIndex--;
                        }
                        if (startIndex >= endIndex) palindromePairList.add(Arrays.asList(j, i));
                    }
                }
            }
        }
        return palindromePairList;
    }

    public List<List<Integer>> palindromePairsHashTable(String[] words) {
        if (words == null) return null;
        int n = words.length;
        List<List<Integer>> palindromePairList = new ArrayList<>();
        Map<String, List<Integer>> wordsIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (wordsIndex.containsKey(words[i])) wordsIndex.get(words[i]).add(i);
            else wordsIndex.put(words[i], new ArrayList<>(Collections.singletonList(i)));
        }
        for (int i = 0; i < n; i++) {
            String temp = words[i];
            int wordLength = temp.length();
            for (int j = 1; j < wordLength; j++) {
                String formerReverse = reverseString(temp.substring(0, j));
                if (checkPalindrome(temp.substring(j, wordLength)) && wordsIndex.containsKey(formerReverse)) {
                    for (int k : wordsIndex.get(formerReverse)) {
                        if (k != i) {
                            palindromePairList.add(Arrays.asList(i, k));
                        }
                    }
                }
                String latterReverse = reverseString(temp.substring(j, wordLength));
                if (checkPalindrome(temp.substring(0, j)) && wordsIndex.containsKey(latterReverse)) {
                    for (int k : wordsIndex.get(latterReverse)) if (k != i) palindromePairList.add(Arrays.asList(k, i));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            String word = words[i];
            String reverseWord = reverseString(word);
            if (wordsIndex.containsKey(reverseWord)) {
                for (int j : wordsIndex.get(reverseWord)) {
                    if (j != i) palindromePairList.add(Arrays.asList(i, j));
                }
            }
        }
        if (wordsIndex.containsKey("")) {
            for (int i = 0; i < n; i++) {
                if (checkPalindrome(words[i])) {
                    for (int j : wordsIndex.get("")) {
                        if (j != i) palindromePairList.add(Arrays.asList(i, j));
                        if (j != i) palindromePairList.add(Arrays.asList(j, i));
                    }
                }
            }
        }
        return palindromePairList;
    }

    private boolean checkPalindrome(String word) {
        int startIndex = 0;
        int endIndex = word.length() - 1;
        while (startIndex < endIndex) {
            if (word.charAt(startIndex) != word.charAt(endIndex)) return false;
            startIndex++;
            endIndex--;
        }
        return true;
    }

    private String reverseString(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            output.append(input.charAt(i));
        }
        return output.toString();
    }

    /**
     * Given a pattern and a string str, find if str follows the same pattern.
     * <p>
     * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word
     * in str.
     * <p>
     * Examples:
     * pattern = "abba", str = "dog cat cat dog" should return true.
     * pattern = "abba", str = "dog cat cat fish" should return false.
     * pattern = "aaaa", str = "dog cat cat dog" should return false.
     * pattern = "abba", str = "dog dog dog dog" should return false.
     * Notes:
     * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single
     * space.
     *
     * @param pattern a string pattern
     * @param str     Check whether this string follows the pattern
     * @return true or false
     */
    public boolean wordPattern(String pattern, String str) {
        String[] patternInfo = new String[26];
        String[] strList = str.split(" ");
        HashSet<String> containsString = new HashSet<>();
        if (strList.length != pattern.length()) return false;
        for (int i = 0; i < strList.length; i++) {
            int patternIndex = pattern.charAt(i) - 'a';
            if (patternInfo[patternIndex] == null) {
                if (containsString.contains(strList[i])) return false;
                containsString.add(strList[i]);
                patternInfo[patternIndex] = strList[i];
            } else if (!patternInfo[patternIndex].equals(strList[i])) return false;
        }
        return true;
    }

    /**
     * Given a non-empty array of integers, return the k most frequent elements.
     * <p>
     * For example,
     * Given [1,1,1,2,2,3] and k = 2, return [1,2].
     * <p>
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
     *
     * @param nums a non-empty array of integers
     * @param k    the k most frequent element
     * @return the k most frequent element
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num :
                nums) {
            if (hashMap.containsKey(num)) hashMap.put(num, hashMap.get(num) + 1);
            else hashMap.put(num, 1);
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(hashMap.size(), new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for (int key :
                hashMap.keySet()) {
            priorityQueue.add(new int[]{key, hashMap.get(key)});
        }
        List<Integer> integerList = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            integerList.add(priorityQueue.poll()[0]);
        }
        return integerList;
    }

    /**
     * Given an array of integers, find if the array contains any duplicates. Your function should return true if any
     * value appears at least twice in the array, and it should return false if every element is distinct.
     *
     * @param nums  an array of integers
     * @return that array contains duplicate or not
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 1) return false;
        HashSet<Integer> numSet = new HashSet<>();
        for (int num: nums) {
            if (numSet.contains(num)) return true;
            numSet.add(num);
        }
        return false;
    }
}
