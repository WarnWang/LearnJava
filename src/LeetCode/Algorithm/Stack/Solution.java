package LeetCode.Algorithm.Stack;

import LeetCode.DataTypes.NestedInteger;

import java.util.Stack;
import java.util.zip.Inflater;

/**
 * Created by warn on 2/6/2016.
 * Store solution to tag stack
 */
public class Solution {
    private int i;

    /**
     * Given an absolute path for a file (Unix-style), simplify it.
     * <p>
     * For example,
     * path = "/home/", => "/home"
     * path = "/a/./b/../../c/", => "/c"
     * click to show corner cases.
     * <p>
     * Corner Cases:
     * Did you consider the case where path = "/../"?
     * In this case, you should return "/".
     * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
     * In this case, you should ignore redundant slashes and return "/home/foo".
     *
     * @param path a string represent of a path
     * @return simplified path
     */
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return null;
        String[] dirList = path.split("/");
        String[] newDirList = new String[dirList.length];
        int dirIndex = 0;
        for (String dir : dirList) {
            if (dir == null || dir.length() == 0 || dir.equals(".")) continue;
            if (dir.equals("..")) {
                if (dirIndex > 0) dirIndex--;
            } else {
                newDirList[dirIndex++] = dir;
            }
        }
        if (dirIndex == 0) return "/";
        StringBuilder newPath = new StringBuilder();
        for (int i = 0; i < dirIndex; i++) {
            newPath.append("/");
            newPath.append(newDirList[i]);
        }
        return newPath.toString();
    }

    /**
     * Given an encoded string, return it's decoded string.
     * <p>
     * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
     * exactly k times. Note that k is guaranteed to be a positive integer.
     * <p>
     * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed,
     * etc.
     * <p>
     * Furthermore, you may assume that the original data does not contain any digits and that digits are only for
     * those repeat numbers, k. For example, there won't be input like 3a or 2[4].
     * <p>
     * Examples:
     * <p>
     * s = "3[a]2[bc]", return "aaabcbc".
     * s = "3[a2[c]]", return "accaccacc".
     * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     *
     * @param s an encoded string
     * @return it's decoded string
     */
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder newString = new StringBuilder();
        for (i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int start = s.indexOf('[', i);
                int repeatTime = Integer.parseInt(s.substring(i, start));
                newString.append(decodeString(s, repeatTime, start + 1));
            } else {
                newString.append(c);
            }
        }
        return newString.toString();
    }

    private String decodeString(String s, int repeatTimes, int startIndex) {

        StringBuilder newString = new StringBuilder();
        for (i = startIndex; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') break;
            else if (Character.isDigit(c)) {
                int start = s.indexOf('[', i);
                int repeatTime = Integer.parseInt(s.substring(i, start));
                newString.append(decodeString(s, repeatTime, start + 1));
            } else newString.append(c);
        }
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < repeatTimes; j++) {
            result.append(newString);
        }
        return result.toString();
    }
}
