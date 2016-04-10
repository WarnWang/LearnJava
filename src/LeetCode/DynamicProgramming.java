package LeetCode;

import java.util.Arrays;

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

    /**
     * You are given coins of different denominations and a total amount of money amount. Write a function to compute
     * the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by
     * any combination of the coins, return -1.
     *
     * @param coins a array of coins
     * @param amount target amount
     * @return minimal coin numbers
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] numOfAmount = new int[amount];
        for (int i: coins) {
            if (i < amount) numOfAmount[i - 1] = 1;
            else if (i == amount) return 1;
        }

        for (int i = 0; i < amount; i++) {
            if (numOfAmount[i] == 0) {
                int minCoin = Integer.MAX_VALUE;
                for (int j: coins) {
                    if (j > i) continue;
                    if (numOfAmount[i - j] != -1) minCoin = Integer.min(minCoin, numOfAmount[i - j]);
                }
                if (minCoin == Integer.MAX_VALUE) numOfAmount[i] = -1;
                else numOfAmount[i] = minCoin + 1;
            }
        }
        return numOfAmount[amount - 1];
    }
}
