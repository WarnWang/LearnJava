package LeetCode.WeeklyContest.Contest5;

import java.util.Arrays;

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
        int n = num.length();
        char[] newNum = new char[n - k];
        int index = 0;
        newNum[0] = num.charAt(0);
        for (int i = 1; i < n; i++) {
            char c = num.charAt(i);
            char d = newNum[index];
            if (d <= c) {
                if (index < newNum.length) {
                    newNum[++index] = c;
                } else k--;
            } else {
                for (int j = index; j >= 0; j--) {
                    if (newNum[j] <= c || k == 0) {
                        newNum[j + 1] = c;
                        index = j + 1;
                        break;
                    } else if (j == 0) {
                        newNum[j] = c;
                        index = 0;
                        k--;
                    } else k--;
                }
            }
            System.out.println(Arrays.toString(newNum));
        }
        int offset = 0;
        int count = newNum.length;
        for (char c : newNum) {
            if (c == '0') {
                offset++;
                count--;
            } else break;
        }
        String minNum = new String(newNum, offset, count);
        if (minNum.isEmpty()) return "0";
        else return minNum;
    }
}
