package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by warn on 27/3/2016.
 * Use to store puzzles about string
 */
public class TagString {
    /**
     * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and
     * return the shortest palindrome you can find by performing this transformation.
     *
     * @param s a string S
     * @return the shortest palindrome you can find by performing this transformation
     */
    public String shortestPalindrome(String s) {
        if (s == null) return null;
        int stringLength = s.length();
        if (stringLength == 1) return s;
        int startIndex;
        int[] exploredLength = new int[stringLength];
        char[] strArray = s.toCharArray();
        for (startIndex = stringLength; startIndex > 1; startIndex--) {
            int i, j;
            boolean stopHere = true;
            if ((startIndex & 1) == 0) {
                i = startIndex / 2 - 1 - exploredLength[startIndex - 1];
                j = startIndex / 2 + exploredLength[startIndex - 1];
            } else {
                i = startIndex / 2 - 1 - exploredLength[startIndex - 1];
                j = startIndex / 2 + 1 + exploredLength[startIndex - 1];
            }
            while (i >= 0) {
                if (strArray[i--] != strArray[j++]) {
                    stopHere = false;
                    exploredLength[startIndex - 1] = startIndex / 2 - i - 2;
                    break;
                }
            }
            if (stopHere) break;
            else {
                int index = startIndex - 2;
                int mirrorIndex = startIndex;
                for (int bound = exploredLength[startIndex - 1] - 1; bound > 0; bound--) {
                    if (index < 0 || mirrorIndex >= stringLength) break;
                    exploredLength[index--] = Integer.min(bound, exploredLength[mirrorIndex++]);
                }
            }
        }
        if (startIndex == stringLength) return s;
        else {
            StringBuilder palindrome = new StringBuilder();
            for (int i = stringLength - 1; i >= startIndex; i--) {
                palindrome.append(strArray[i]);
            }
            palindrome.append(s);
            return palindrome.toString();
        }
    }

    /**
     * Given two strings s and t, write a function to determine if t is an anagram of s.
     * For example,
     * s = "anagram", t = "nagaram", return true.
     * s = "rat", t = "car", return false.
     *
     * @param s one string
     * @param t another string
     * @return whether these two string are anagram
     */
    public boolean isAnagram(String s, String t) {
        if (s == null && t == null) return true;
        else if (s == null) return false;
        else if (t == null) return false;
        char[] sCharArray = s.toCharArray(), tCharArray = t.toCharArray();
        if (sCharArray.length != tCharArray.length) return false;
        Arrays.sort(sCharArray);
        Arrays.sort(tCharArray);
        for (int i = 0; i < sCharArray.length; i++) {
            if (sCharArray[i] != tCharArray[i]) return false;
        }
        return true;
    }

    /**
     * Implement strStr().
     * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     *
     * @param haystack a string
     * @param needle   target string
     * @return the index of first occurrence of needle in haystack
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        int lenHaystack = haystack.length();
        int lenNeedle = needle.length();
        if (lenNeedle == 0) return 0;
        if (lenHaystack == 0 || lenHaystack < lenNeedle) return -1;
        HashMap<Character, Integer> lastOccurrence = new HashMap<>();
        char[] haystackCharArray = haystack.toCharArray();
        char[] needleCharArray = needle.toCharArray();
        for (char c : haystackCharArray) lastOccurrence.put(c, -1);
        for (int i = 0; i < lenNeedle; i++) {
            if (!lastOccurrence.containsKey(needleCharArray[i])) return -1;
            lastOccurrence.put(needleCharArray[i], i);
        }

        int i = lenNeedle - 1;                                     // an index into the text
        int k = lenNeedle - 1;                                     // an index into the pattern
        while (i < lenHaystack) {
            if (haystackCharArray[i] == needleCharArray[k]) {                   // a matching character
                if (k == 0) return i;                        // entire pattern has been found
                i--;                                         // otherwise, examine previous
                k--;                                         // characters of text/pattern
            } else {
                i += lenNeedle - Math.min(k, 1 + lastOccurrence.get(haystackCharArray[i])); // case analysis for jump step
                k = lenNeedle - 1;                                   // restart at end of pattern
            }
        }
        return -1;
    }

    /**
     * Implement a basic calculator to evaluate a simple expression string.
     * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer
     * division should truncate toward zero.
     * You may assume that the given expression is always valid.
     * https://leetcode.com/problems/basic-calculator-ii/
     *
     * @param s a simple expression string
     * @return the answer of the experssion
     */
    public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        char[] expression = s.toCharArray();
        int n = 0;
        char lastOperator = '+';
        int left = 0, right = 0;
        for (char c : expression) {
            if (Character.isDigit(c)) n = n * 10 + c - '0';
            else if (c != ' ') {
                switch (lastOperator) {
                    case '+':
                        left += right;
                        right = n;
                        break;
                    case '-':
                        left += right;
                        right = -n;
                        break;
                    case '*':
                        right *= n;
                        break;
                    case '/':
                        right /= n;
                }
                lastOperator = c;
                n = 0;
            }
        }
        if (lastOperator == '+') return left + right + n;
        else if (lastOperator == '-') return left + right - n;
        else if (lastOperator == '*') return left + right * n;
        else return left + right / n;
    }

    /**
     * Write a function that takes a string as input and reverse only the vowels of a string.
     * <p>
     * Example 1:
     * Given s = "hello", return "holle".
     * <p>
     * Example 2:
     * Given s = "leetcode", return "leotcede".
     *
     * @param s a string
     * @return the reverse vowel version
     */
    public String reverseVowels(String s) {
        if (s == null || s.length() < 2) return s;
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        HashSet<Character> vowel = new HashSet<>();
        boolean[] isVowel = new boolean[26];
        isVowel[0] = true;
        isVowel['e' - 'a'] = true;
        isVowel['i' - 'a'] = true;
        isVowel['o' - 'a'] = true;
        isVowel['u' - 'a'] = true;
        while (i < j) {
            char iC = chars[i];
            char jC = chars[j];
            boolean iIsVowel = Character.isAlphabetic(iC) && ((iC < 'a' && isVowel[iC - 'A'])
                    || (iC >= 'a' && isVowel[iC - 'a']));
            boolean jIsVowel = Character.isAlphabetic(jC) && ((jC < 'a' && isVowel[jC - 'A'])
                    || (jC >= 'a' && isVowel[jC - 'a']));
            if (iIsVowel && jIsVowel) {
                char tmp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = tmp;
            } else {
                if (!iIsVowel) i++;
                if (!jIsVowel) j--;
            }
        }
//        return new String(chars);
        return String.valueOf(chars);
    }
}
