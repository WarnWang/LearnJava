package LeetCode.WeeklyContest.Contest5;

/**
 * Created by warn on 18/9/2016.
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is
 * the smallest possible.
 * <p>
 * Note:
 * The length of num is less than 105 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if (k == 0) return num;
        else if (num == null || num.length() == 0 || num.length() == k) return "0";
        String result = num;
        for (int i = 0; i < k; i++) {
            String nextResult = result.substring(1);
            int n = result.length();
            for (int j = 1; j < n - 1; j++) {
                String newString = result.substring(0, j) + result.substring(j + 1, n);
                if (newString.compareTo(nextResult) < 0) nextResult = newString;
            }
            String newString = result.substring(0, n - 1);
            if (newString.compareTo(nextResult) < 0) nextResult = newString;
            result = nextResult;
        }
        if (result.startsWith("0")) result = result.substring(1);
        if (result.isEmpty()) return "0";
        else return result;
    }
}
