package LeetCode;

import java.util.Arrays;
import java.util.HashMap;

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
}
