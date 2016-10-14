package LeetCode.Algorithm.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by warn on 24/7/2016.
 * Store solution to string questions
 */
public class Solution2 {
    public String countAndSay(int n) {
        if (n <= 1) return "1";
        String sayString = "1";
        for (int i = 1; i < n; i++) {
            sayString = getNextCountAndSay(sayString);
        }
        return sayString;
    }

    private String getNextCountAndSay(String numString) {
        char[] numCharArray = numString.toCharArray();
        StringBuilder sayString = new StringBuilder();
        char last = 'a';
        int count = 0;
        for (char c : numCharArray) {
            if (c != last) {
                if (count > 0) {
                    sayString.append(count);
                    sayString.append(last);
                }
                last = c;
                count = 1;
            }
            count++;
        }
        if (count > 0) {
            sayString.append(count);
            sayString.append(last);
        }
        return sayString.toString();
    }

    /**
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of
     * last word in the string.
     * <p>
     * If the last word does not exist, return 0.
     * <p>
     * Note: A word is defined as a character sequence consists of non-space characters only.
     * <p>
     * For example,
     * Given s = "Hello World",
     * return 5.
     *
     * @param s a string s
     * @return last word length
     */
    public int lengthOfLastWordRegex(String s) {
        if (s == null || s.length() == 0) return 0;

        // Reverse string so as to find the last word
        String reverseString = new StringBuilder(s).reverse().toString();
        Pattern r = Pattern.compile("(\\S+)");
        Matcher m = r.matcher(reverseString);

        if (m.find()) return m.group().length();
        else return 0;
    }

    public int lengthOfLastWordCharOperation(String s) {
        if (s == null || s.length() == 0) return 0;
        int index = s.length() - 1;
        while (index >= 0 && s.charAt(index) == ' ') index--;
        int count = 0;
        for (int i = index; i >= 0; i--) {
            if (s.charAt(i) == ' ') break;
            count++;
        }
        return count;
    }

    /**
     * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting
     * indices of substring(s) in s that is a concatenation of each word in words exactly once and without any
     * intervening characters.
     * <p>
     * For example, given:
     * s: "barfoothefoobarman"
     * words: ["foo", "bar"]
     * <p>
     * You should return the indices: [0,9].
     * (order does not matter).
     *
     * @param s     a string
     * @param words a word list
     * @return the index of substring that is a concatenation of each word
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> startIndexList = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return startIndexList;
        HashMap<String, Integer> containsWord = new HashMap<>();
        for (String word : words)
            if (containsWord.containsKey(word)) containsWord.put(word, containsWord.get(word) + 1);
            else containsWord.put(word, 1);
        char[] chars = s.toCharArray();
        int wordLength = words[0].length();
        for (int i = 0, n = chars.length - wordLength * words.length + 1; i < n; i++) {
            String newString = new String(chars, i, wordLength);
            if (containsWord.containsKey(newString)) {
                HashMap<String, Integer> contains = new HashMap<>(containsWord);
                int value = containsWord.get(newString);
                if (value == 1) contains.remove(newString);
                else contains.put(newString, value - 1);
                if (contains.isEmpty()) startIndexList.add(i);
                else
                    for (int j = i + wordLength, m = chars.length - wordLength + 1; j < m; j += wordLength) {
                        String subString = new String(chars, j, wordLength);
                        if (!contains.containsKey(subString)) break;
                        int nSub = contains.get(subString);
                        if (nSub == 1) {
                            contains.remove(subString);
                            if (contains.isEmpty()) {
                                startIndexList.add(i);
                                break;
                            }
                        } else contains.put(subString, nSub - 1);
                    }
            }
        }
        return startIndexList;
    }

    /**
     * Given two strings s and t which consist of only lowercase letters.
     * <p>
     * String t is generated by random shuffling string s and then add one more letter at a random position.
     * <p>
     * Find the letter that was added in t.
     *
     * @param s one string
     * @param t another string
     * @return the padding string
     */
    public char findTheDifference(String s, String t) {
        HashMap<Character, Integer> paddingCharacter = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (paddingCharacter.containsKey(c)) paddingCharacter.put(c, paddingCharacter.get(c) + 1);
            else paddingCharacter.put(c, 1);
        }

        for (char c : t.toCharArray()) {
            if (paddingCharacter.containsKey(c)) {
                int value = paddingCharacter.get(c);
                if (value == 1) paddingCharacter.remove(c);
                else paddingCharacter.put(c, value - 1);
            } else {
                return c;
            }
        }
        return (char) paddingCharacter.keySet().toArray()[0];
    }

    public char findTheDifferenceArray(String s, String t) {
        int[] paddingArray = new int[26];
        for (char c : s.toCharArray()) {
            paddingArray[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            paddingArray[c - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (paddingArray[i] == -1) return (char) ('a' + i);
        }
        return 'a';
    }

    /**
     * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once
     * and only once. You must make sure your result is the smallest in lexicographical order among all possible
     * results.
     * <p>
     * Example:
     * Given "bcabc"
     * Return "abc"
     * <p>
     * Given "cbacdcbc"
     * Return "acdb"
     *
     * @param s a string contains only lowercase letters
     * @return the smallest in lexicographical order
     */
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return "";
        int[] charCount = new int[26];
        boolean[] isUsed = new boolean[26];
        char[] chars = s.toCharArray();
        for (char c : chars) charCount[c - 'a']++;

        int stringLength = 0;
        for (int i : charCount) if (i > 0) stringLength++;
        char[] result = new char[stringLength];
        int resultIndex = 0;
        for (char c : chars) {
            int cIndex = c - 'a';
            charCount[cIndex]--;
            if (isUsed[cIndex]) continue;
            for (int i = resultIndex - 1; i >= 0; i--) {
                char cc = result[i];
                int ccIndex = cc - 'a';
                if (cc > c && charCount[ccIndex] > 0) {
                    System.arraycopy(result, i + 1, result, i, resultIndex - i);
                    isUsed[ccIndex] = false;
                    resultIndex--;
                } else break;
            }
            result[resultIndex++] = c;
            isUsed[cIndex] = true;
        }
        return new String(result);
    }

    /**
     * Compare two version numbers version1 and version2.
     * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
     * <p>
     * You may assume that the version strings are non-empty and contain only digits and the . character.
     * The . character does not represent a decimal point and is used to separate number sequences.
     * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision
     * of the second first-level revision.
     * <p>
     * Here is an example of version numbers ordering:
     * <p>
     * 0.1 < 1.1 < 1.2 < 13.37
     *
     * @param version1 the first version number
     * @param version2 the second version number
     * @return which one is bigger
     */
    public int compareVersion(String version1, String version2) {
        if (version1 == null && version2 == null) return 0;
        else if (version1 == null) return -1;
        else if (version2 == null) return 1;

        int len1 = version1.length(), len2 = version2.length();
        int i1 = 0, i2 = 0;
        while (i1 < len1 || i2 < len2) {
            int n1 = 0, n2 = 0;
            for (int i = i1; i < len1; i++) {
                if (version1.charAt(i) == '.') {
                    n1 = Integer.parseInt(version1.substring(i1, i));
                    i1 = i + 1;
                    break;
                } else if (i == len1 - 1) {
                    n1 = Integer.parseInt(version1.substring(i1, len1));
                    i1 = len1;
                }
            }

            for (int i = i2; i < len2; i++) {
                if (version2.charAt(i) == '.') {
                    n2 = Integer.parseInt(version2.substring(i2, i));
                    i2 = i + 1;
                    break;
                } else if (i == len2 - 1) {
                    n2 = Integer.parseInt(version2.substring(i2, len2));
                    i2 = len2;
                }
            }
            if (n1 > n2) return 1;
            else if (n2 > n1) return -1;
        }
        return 0;
    }

    /**
     * Suppose we abstract our file system by a string in the following manner:
     * <p>
     * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
     * <p>
     * dir
     * subdir1
     * subdir2
     * file.ext
     * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
     * <p>
     * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
     * <p>
     * dir
     * subdir1
     * file1.ext
     * subsubdir1
     * subdir2
     * subsubdir2
     * file2.ext
     * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an
     * empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing
     * a file file2.ext.
     * <p>
     * We are interested in finding the longest (number of characters) absolute path to a file within our file system.
     * For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and
     * its length is 32 (not including the double quotes).
     * <p>
     * Given a string representing the file system in the above format, return the length of the longest absolute path
     * to file in the abstracted file system. If there is no file in the system, return 0.
     * <p>
     * Note:
     * The name of a file contains at least a . and an extension.
     * The name of a directory or sub-directory will not contain a ..
     *
     * @param input a file system string
     * @return the longest absolute path
     */
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) return 0;
        String[] pathList = input.split("\\n");
        String[] path = new String[pathList.length];
        int level;
//        String longest = "";
        int longestLength = 0;
        for (String i : pathList) {
            String removeUseLess = i.replaceAll("\\t", "");
            level = i.length() - removeUseLess.length();
            path[level] = removeUseLess;
            if (removeUseLess.contains(".")) {
                StringBuilder newString = new StringBuilder();
                for (int j = 0; j < level; j++) {
                    newString.append(path[j]);
                    newString.append('/');
                }
                newString.append(removeUseLess);
                if (newString.length() > longestLength) {
                    longestLength = newString.length();
//                    longest = newString.toString();
                }
            }
        }
        return longestLength;
    }

    /**
     *  Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a
     * function  that  will  return  true  if  the  ransom  note  can  be  constructed  from  the  magazines ;  otherwise,  it  will
     *  return  false.
     * <p>
     * Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
     * <p>
     * Note:
     * You may assume that both strings contain only lowercase letters.
     * <p>
     * canConstruct("a", "b") -> false
     * canConstruct("aa", "ab") -> false
     * canConstruct("aa", "aab") -> true
     *
     * @param ransomNote a random note
     * @param magazine   a magazine
     * @return whether the  ransom  note  can  be  constructed  from  the  magazines.
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0) return true;
        if (magazine == null || magazine.length() == 0 || magazine.length() < ransomNote.length()) return false;
        char[] rArray = ransomNote.toCharArray();
        char[] mArray = magazine.toCharArray();
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : mArray) {
            if (charCount.containsKey(c)) charCount.put(c, charCount.get(c) + 1);
            else charCount.put(c, 1);
        }

        for (char c : rArray) {
            if (charCount.containsKey(c)) {
                int value = charCount.get(c);
                if (value == 1) charCount.remove(c);
                else charCount.put(c, value - 1);
            } else return false;
        }
        return true;
    }

    /**
     * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that
     * every character in T appears no less than k times.
     * <p>
     * Example 1:
     * <p>
     * Input:
     * s = "aaabb", k = 3
     * <p>
     * Output:
     * 3
     * <p>
     * The longest substring is "aaa", as 'a' is repeated 3 times.
     * Example 2:
     * <p>
     * Input:
     * s = "ababbc", k = 2
     * <p>
     * Output:
     * 5
     * <p>
     * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
     *
     * @param s an input string
     * @param k the least character length
     * @return the size of all substring
     */
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) return 0;
        return getLongestSubstring(s.toCharArray(), k, 0, s.length());
    }

    private int getLongestSubstring(char[] s, int k, int start, int end) {
        if (end - start < k) return 0;
        int[] charCount = new int[26];
        HashSet<Character> noNeedChar = new HashSet<>();
        for (int i = start; i < end; i++) {
            int index = s[i] - 'a';
            if (charCount[index] == 0) noNeedChar.add(s[i]);
            charCount[index]++;
            if (charCount[index] == k) noNeedChar.remove(s[i]);
        }
        if (noNeedChar.isEmpty()) return end - start;
        int cur = start;
        int maxLength = 0;
        for (int i = start; i < end; i++) {
            if (noNeedChar.contains(s[i])) {
                maxLength = Math.max(maxLength, getLongestSubstring(s, k, cur, i));
                cur = i + 1;
            }
        }
        maxLength = Math.max(maxLength, getLongestSubstring(s, k, cur, end));
        return maxLength;
    }

    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
     * display this pattern in a fixed font for better legibility)
     * <p>
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * <p>
     * string convert(string text, int nRows);
     * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     *
     * @param s       a input string
     * @param numRows rows number
     * @return converted string
     */
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) return "";
        else if (numRows == 1 || numRows >= s.length()) return s;

        char[] result = new char[s.length()];
        int blockSize = numRows - 2 + numRows;

        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < result.length; j += blockSize) {
                result[index++] = s.charAt(j);
                if (i > 0 && i < numRows - 1) {
                    int newJ = j + blockSize - i * 2;
                    if (newJ < result.length)
                        result[index++] = s.charAt(newJ);
                }
            }
        }

        return new String(result);
    }
}
