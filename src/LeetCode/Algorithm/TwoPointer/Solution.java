package LeetCode.Algorithm.TwoPointer;

/**
 * Created by warn on 29/9/2016.
 * Store problem with tag two pointer
 */
public class Solution {
    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * <p>
     * For example,
     * "A man, a plan, a canal: Panama" is a palindrome.
     * "race a car" is not a palindrome.
     *
     * @param s a string
     * @return whether this string is palindrome
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        for (int i = 0, j = s.length() - 1; i < j;) {
            char cI = s.charAt(i), cJ = s.charAt(j);
            while (!Character.isAlphabetic(cI) || !Character.isDigit(cI)) {
                i++;
                if (i >= j) return true;
                cI = s.charAt(i);
            }

            while (!Character.isAlphabetic(cJ) || !Character.isDigit(cJ)) {
                j--;
                if (i >= j) return true;
                cJ = s.charAt(j);
            }
            if (Character.toLowerCase(cI) != Character.toLowerCase(cJ)) return false;
            else {
                i++;
                j--;
            }
        }
        return true;
    }
}
