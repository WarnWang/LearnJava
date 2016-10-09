package LeetCode.WeeklyContest.Contest8;

import java.util.ArrayList;

/**
 * Created by warn on 9/10/2016.
 * Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
 * <p>
 * Note:
 * <p>
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        if (num1 == null) return num2;
        else if (num2 == null) return num1;
        int i1 = num1.length() - 1, i2 = num2.length() - 1;
        ArrayList<Character> result = new ArrayList<>();
        int carry = 0;
        while (i1 >= 0 && i2 >= 0) {
            int c1 = num1.charAt(i1) - '0', c2 = num2.charAt(i2) - '0';
            result.add(0, (char) ((c1 + c2 + carry) % 10 + '0'));
            carry = (c1 + c2 + carry) / 10;
            i1--;
            i2--;
        }
        while (i1 >= 0) {
            int c1 = num1.charAt(i1) - '0';
            result.add(0, (char) ((c1 + carry) % 10 + '0'));
            carry = (c1 + carry) / 10;
            i1--;
        }

        while (i2 >= 0) {
            int c1 = num2.charAt(i2) - '0';
            result.add(0, (char) ((c1 + carry) % 10 + '0'));
            carry = (c1 + carry) / 10;
            i2--;
        }

        if (carry != 0) result.add(0, (char) (carry + '0'));
        StringBuilder resultStr = new StringBuilder();
        for (char c: result) resultStr.append(c);
        return resultStr.toString();
    }
}
