package LeetCode.Algorithm.String;

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
        for (char c: numCharArray) {
            if (c != last) {
                if (count > 0) {
                    sayString.append(count);
                    sayString.append(last);
                }
                last = c;
                count = 1;
            } count++;
        }
        if (count > 0) {
            sayString.append(count);
            sayString.append(last);
        }
        return sayString.toString();
    }
}
