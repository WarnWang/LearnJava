package LeetCode.Algorithm.String;

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
}
