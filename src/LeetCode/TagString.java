package LeetCode;

import java.util.*;

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

    public String reverseString(String s) {
        if (s == null || s.length() < 2) return s;
        char[] sCharArray = s.toCharArray();
        for (int i = 0, j = sCharArray.length - 1; i < j; i++, j--) {
            char tmp = s.charAt(i);
            sCharArray[i] = s.charAt(j);
            sCharArray[j] = tmp;
        }
        return new String(sCharArray);
    }

    /**
     * Given a digit string, return all possible letter combinations that the number could represent.
     *
     * @param digits a digit string
     * @return all possible letter combinations the number could represent
     */
    public List<String> letterCombinations(String digits) {
        ArrayList<String> combinations = new ArrayList<>();
        if (digits == null || digits.length() == 0) return combinations;
        char[] digitArray = digits.toCharArray();
//        for (char c: digitArray) if (!Character.isDigit(c) || c - '0' < 2) return combinations;
        letterCombinations(digitArray, new char[digitArray.length], 0, combinations);
        return combinations;
    }

    private void letterCombinations(char[] digitArray, char[] tempString, int index,
                                    ArrayList<String> combinations) {
        if (index == digitArray.length) combinations.add(new String(tempString));
        else {
            char c = digitArray[index];
            switch (c) {
                case '2':
                    tempString[index] = 'a';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'b';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'c';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    break;
                case '3':
                    tempString[index] = 'd';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'e';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'f';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    break;
                case '4':
                    tempString[index] = 'g';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'h';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'i';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    break;
                case '5':
                    tempString[index] = 'j';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'k';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'l';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    break;
                case '6':
                    tempString[index] = 'm';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'n';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'o';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    break;
                case '7':
                    tempString[index] = 'p';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'q';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'r';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 's';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    break;
                case '8':
                    tempString[index] = 't';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'u';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'v';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    break;
                case '9':
                    tempString[index] = 'w';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'x';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'y';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    tempString[index] = 'z';
                    letterCombinations(digitArray, tempString, index + 1, combinations);
                    break;
            }
        }
    }

    /**
     * Related to question Excel Sheet Column Title
     * <p>
     * Given a column title as appear in an Excel sheet, return its corresponding column number.
     * <p>
     * For example:
     * <p>
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     *
     * @param s Excel Sheet Column index
     * @return corresponding column number
     */
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) return 0;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            index = index * 26 + ((c >= 'a') ? (c - 'a') : (c - 'A')) + 1;
        }
        return index;
    }

    /**
     * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
     * <p>
     * For example:
     * <p>
     * 1 -> A
     * 2 -> B
     * 3 -> C
     * ...
     * 26 -> Z
     * 27 -> AA
     * 28 -> AB
     *
     * @param n the column index
     * @return the column title
     */
    public String convertToTitle(int n) {
        StringBuilder a = new StringBuilder();
        while (n > 0) {
            if (n % 26 == 0){
                a.append('Z');
                n = n / 26 - 1;
            } else {
                a.append((char) (n % 26 + 'A' - 1));
                n /= 26;
            }
        }
        return a.reverse().toString();
    }
}
