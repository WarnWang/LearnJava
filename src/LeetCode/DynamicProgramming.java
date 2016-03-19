package LeetCode;

/**
 * Created by warn on 19/3/2016.
 * Use to store those dynamic programming puzzles
 */
public class DynamicProgramming {

    /**
     * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of
     * 1's in their binary representation and return them as an array.
     * https://leetcode.com/problems/counting-bits/
     *
     * @param num a non negative integer number
     * @return For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation
     * and return them as an array
     */
    public int[] countBits(int num) {
        int[] bitArray = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bitArray[i] = (i & 1) + bitArray[i >> 1];
        }
        return bitArray;
    }

    /**
     * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the
     * Hamming weight).
     * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function
     * should return 3.
     *
     * @param n an unsigned integer
     * @return the number of ’1' bits it has
     */
    public int hammingWeight(int n) {
        int nBit1 = 0;
        while (n != 0) {
            if (n < 0) {
                nBit1 += Integer.remainderUnsigned(n, 2);
                n = Integer.divideUnsigned(n, 2);
            } else {
                nBit1 += n & 1;
                n >>= 1;
            }
        }
        return nBit1;
    }
}
