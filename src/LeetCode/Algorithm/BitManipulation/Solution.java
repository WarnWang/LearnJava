package LeetCode.Algorithm.BitManipulation;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
     * exactly twice. Find the two elements that appear only once.
     * <p>
     * For example:
     * <p>
     * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
     * <p>
     * Note:
     * The order of the result is not important. So in the above example, [5, 3] is also correct.
     * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space
     * complexity?
     *
     * @param nums an array of numbers nums
     * @return two elements that appear only once.
     */
    public int[] singleNumber3(int[] nums) {
        if (nums.length == 2) return nums;
        int[] result = new int[2];
        int ones = 0;
        for (int num : nums) ones ^= num;

        int last = ones & (~(ones - 1));
        for (int num : nums)
            if ((num & last) == last) result[1] ^= num;
            else result[0] ^= num;
        return result;
    }

    /**
     * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method
     * is used.
     * <p>
     * Note:
     * <p>
     * All letters in hexadecimal (a-f) must be in lowercase.
     * The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single
     * zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
     * The given number is guaranteed to fit within the range of a 32-bit signed integer.
     * You must not use any method provided by the library which converts/formats the number to hex directly.
     * Example 1:
     * <p>
     * Input:
     * 26
     * <p>
     * Output:
     * "1a"
     * Example 2:
     * <p>
     * Input:
     * -1
     * <p>
     * Output:
     * "ffffffff"
     *
     * @param num an integer
     * @return its hex value
     */
    public String toHex(int num) {
        StringBuilder hexString = new StringBuilder();
        char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (num == 0) return "0";
        else if (num > 0) {
            while (num > 0) {
                hexString.insert(0, hexArray[num & 15]);
                num >>= 4;
            }
        } else {
            num = -num - 1;
            for (int i = 0; i < 8; i++) {
                int index = 15 - (num & 15);
                hexString.insert(0, hexArray[index]);
                num >>= 4;
            }
        }
        return hexString.toString();
    }

    /**
     * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
     * <p>
     * Find all the elements that appear twice in this array.
     * <p>
     * Could you do it without extra space and in O(n) runtime?
     * <p>
     * Example:
     * Input:
     * [4,3,2,7,8,2,3,1]
     * <p>
     * Output:
     * [2,3]
     *
     * @param nums an array of integers
     * @return all the elements that appear twice in this array
     */
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> twoNum = new ArrayList<>();
        if (nums == null || nums.length == 0) return twoNum;

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0)
                twoNum.add(index + 1);
            nums[index] = -nums[index];
        }
        return twoNum;
    }
}
