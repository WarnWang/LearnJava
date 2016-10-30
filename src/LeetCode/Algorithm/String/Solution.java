package LeetCode.Algorithm.String;

import java.util.*;

/**
 * Created by warn on 8/6/2016.
 * Solution to puzzles with tag string
 */
public class Solution {
    public static void main(String args[]) {
        Solution test = new Solution();
        System.out.println(test.reverseWords(" aqo kdn   ") + "!");
    }

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

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder reversedString = new StringBuilder();
        int lastNonSpaceIndex = -1;
        for (int n = s.length(), i = n - 1; i > -1; i--) {
            char c = s.charAt(i);
            if (lastNonSpaceIndex == -1 && c != ' ') lastNonSpaceIndex = i + 1;
            else if (c == ' ' && lastNonSpaceIndex != -1) {
                reversedString.append(s.substring(i + 1, lastNonSpaceIndex));
                reversedString.append(' ');
                lastNonSpaceIndex = -1;
            }
        }
        if (lastNonSpaceIndex != -1) reversedString.append(s.substring(0, lastNonSpaceIndex));
        else if (reversedString.length() > 0) reversedString.deleteCharAt(reversedString.length() - 1);
        return reversedString.toString();
    }

    public String reverseWordsSplitString(String s) {
        if (s == null) return "";
        String[] sArray = s.trim().split(" ");
        if (sArray.length < 1) return "";
        StringBuilder reversedString = new StringBuilder(sArray[sArray.length - 1]);
        for (int i = sArray.length - 2; i > -1; i--) {
            if (sArray[i].length() < 1) continue;
            reversedString.append(' ').append(sArray[i]);
        }
        return reversedString.toString();
    }

    /**
     * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest
     * transformation sequence from beginWord to endWord, such that:
     * <p>
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the word list
     * For example,
     * <p>
     * Given:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     * <p>
     * Note:
     * Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     *
     * @param beginWord beginning word
     * @param endWord   end word
     * @param wordList  a word list
     * @return the maximum length of the transformation
     */
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null || endWord == null || beginWord.length() != endWord.length() ||
                !wordList.contains(beginWord) || !wordList.contains(endWord)) return 0;
        if (beginWord.equals(endWord)) return 1;
        HashSet<String> beginSet = new HashSet<>(Collections.singletonList(beginWord));
        HashSet<String> endSet = new HashSet<>(Collections.singletonList(endWord));
        HashSet<String> visited = new HashSet<>();
        int len = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                HashSet<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            HashSet<String> nextBeginSet = new HashSet<>();
            for (String word : beginSet) {
                char[] wordArray = word.toCharArray();
                visited.add(word);
                for (int i = 0, n = wordArray.length; i < n; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char ori = wordArray[i];
                        wordArray[i] = c;
                        String newWord = new String(wordArray);
                        if (wordList.contains(newWord)) {
                            if (endSet.contains(newWord)) return len + 1;
                            if (!visited.contains(newWord)) nextBeginSet.add(newWord);
                        }
                        wordArray[i] = ori;
                    }
                }
            }
            beginSet = nextBeginSet;
            len++;
        }
        return 0;
    }

    /**
     * Write a program that outputs the string representation of numbers from 1 to n.
     * <p>
     * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output
     * “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
     * <p>
     * Example:
     * <p>
     * n = 15,
     * <p>
     * Return:
     * [
     * "1",
     * "2",
     * "Fizz",
     * "4",
     * "Buzz",
     * "Fizz",
     * "7",
     * "8",
     * "Fizz",
     * "Buzz",
     * "11",
     * "Fizz",
     * "13",
     * "14",
     * "FizzBuzz"
     * ]
     *
     * @param n number
     * @return the list of FizzBuzz
     */
    public List<String> fizzBuzz(int n) {
        ArrayList<String> fizzBuzzList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) fizzBuzzList.add("FizzBuzz");
            else if (i % 3 == 0) fizzBuzzList.add("Fizz");
            else if (i % 5 == 0) fizzBuzzList.add("Buzz");
            else fizzBuzzList.add(Integer.toString(i));
        }
        return fizzBuzzList;
    }
}
