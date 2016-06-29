package LeetCode.Algorithm.BitManipulation;

/**
 * Created by warn on 10/6/2016.
 * Solve puzzles of bit manipulation
 */
public class Solution {
    /**
     * Given an array of integers, every element appears twice except for one. Find that single one.
     *
     * @param nums an array of integers
     * @return the only single number
     */
    public int singleNumber(int[] nums) {
        int operator = 0;
        for (int num : nums) operator ^= num;
        return operator;
    }

    /**
     * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range,
     * inclusive.
     * <p>
     * For example, given the range [5, 7], you should return 4.
     *
     * @param m lower range
     * @param n upper range
     * @return the bit and value
     */
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        if (m == n) return m;
        int numberOfNum = n - m + 1;
        int num = 0;
        for (int i = 1; (m >> i) > 0; i++) {
            if (numberOfNum < (1 << i) + 1 && ((m >> i) & 1) == 1 && ((n >> i) & 1) == 1)
                num += (1 << i);
        }
        return num;
    }
}
