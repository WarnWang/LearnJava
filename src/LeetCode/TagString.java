package LeetCode;

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
        for (startIndex = stringLength; startIndex > 1; startIndex--) {
            int i, j;
            boolean stopHere = true;
            if ((startIndex & 1) == 0) {
                i = startIndex / 2 - 1;
                j = i + 1;
            } else {
                i = startIndex / 2 - 1;
                j = i + 2;
            }
            while (i >= 0) {
                if (s.charAt(i) != s.charAt(j)) {
                    stopHere = false;
                    break;
                }
                i--;
                j++;
            }
            if (stopHere) break;
        }
        if (startIndex == stringLength) return s;
        else {
            StringBuilder palindrome = new StringBuilder();
            for (int i = stringLength - 1; i >= startIndex; i--) {
                palindrome.append(s.charAt(i));
            }
            palindrome.append(s);
            return palindrome.toString();
        }
    }
}
