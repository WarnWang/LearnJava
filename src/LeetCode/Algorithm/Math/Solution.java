package LeetCode.Algorithm.Math;

/**
 * Created by warn on 21/6/2016.
 * Save function with tag math
 */
public class Solution {
    /**
     * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
     * <p>
     * Example:
     * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding
     * [11,22,33,44,55,66,77,88,99])
     * https://leetcode.com/problems/count-numbers-with-unique-digits/
     *
     * @param n a non-negative integer n
     * @return the number of all unique digits
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n < 1) return 1;
        if (n >= 10) n = 10;
        int num = 0;
        for (int i = 0; i <= n; i++) {
            int current = 1;
            for (int j = 0; j < i; j++) {
                if (j == 0) current *= 9;
                else current *= (10 - j);
            }
            num += current;
        }
        return num;
    }
}
