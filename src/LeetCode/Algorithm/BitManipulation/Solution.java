package LeetCode.Algorithm.BitManipulation;

import java.util.Arrays;
import java.util.Comparator;

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

    /**
     * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
     * <p>
     * Example:
     * Given a = 1 and b = 2, return 3.
     *
     * @param a one integer
     * @param b another integer
     * @return the sum of these two integer
     */
    public int getSum(int a, int b) {
        int sum = a ^ b;
        int carry = a & b;
        while (carry != 0) {
            int newSum = sum ^ carry;
            carry = sum & carry;
            sum = newSum;
        }
        return sum;
    }

    /**
     * Given an array of integers, every element appears three times except for one. Find that single one.
     * https://discuss.leetcode.com/topic/2926/accepted-code-with-proper-explaination-does-anyone-have-a-better-idea/2
     *
     * @param nums an array of integers
     * @return the only single number
     */
    public int singleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return nums[0];
        int ones = 0, twos = 0;
        for (int num : nums) {
            twos |= (ones & num);
            ones ^= num;
            int commentMask = ~(ones & twos);
            twos &= commentMask;
            ones &= commentMask;
        }
        return ones;
    }

    /**
     * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do
     * not share common letters. You may assume that each word will contain only lower case letters. If no such two
     * words exist, return 0.
     * <p>
     * Example 1:
     * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
     * Return 16
     * The two words can be "abcw", "xtfn".
     * <p>
     * Example 2:
     * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
     * Return 4
     * The two words can be "ab", "cd".
     * <p>
     * Example 3:
     * Given ["a", "aa", "aaa", "aaaa"]
     * Return 0
     * No such pair of words.
     *
     * @param words a list of words
     * @return the max length product
     */
    public int maxProduct(String[] words) {
        int maxLengthProduct = 0;
        if (words != null && words.length > 1) {
            int n = words.length;
            int mask[] = new int[n];
            int[] length = new int[n];
            for (int i = 0; i < n; i++) {
                char[] word = words[i].toCharArray();
                length[i] = word.length;
                for (char c : word) {
                    mask[i] |= 1 << (c - 'a');
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if ((mask[i] & mask[j]) == 0) {
                        maxLengthProduct = Math.max(maxLengthProduct, length[i] * length[j]);
                    }
                }
            }
        }
        return maxLengthProduct;
    }
}
