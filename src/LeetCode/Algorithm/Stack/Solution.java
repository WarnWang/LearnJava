package LeetCode.Algorithm.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by warn on 2/6/2016.
 * Store solution to tag stack
 */
public class Solution {
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
}
