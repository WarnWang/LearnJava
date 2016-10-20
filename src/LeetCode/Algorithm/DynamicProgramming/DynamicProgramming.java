package LeetCode.Algorithm.DynamicProgramming;

/**
 * Created by warn on 19/3/2016.
 * Use to store those dynamic programming puzzles
 */
public class DynamicProgramming {

    static public void main(String[] args) {
        DynamicProgramming test = new DynamicProgramming();
        test.isInterleave("ab", "cd", "acbd");
    }

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
     * @param coins  a array of coins
     * @param amount target amount
     * @return minimal coin numbers
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] numOfAmount = new int[amount];
        for (int i : coins) {
            if (i < amount) numOfAmount[i - 1] = 1;
            else if (i == amount) return 1;
        }

        for (int i = 0; i < amount; i++) {
            if (numOfAmount[i] == 0) {
                int minCoin = Integer.MAX_VALUE;
                for (int j : coins) {
                    if (j <= i && numOfAmount[i - j] != -1) minCoin = Integer.min(minCoin, numOfAmount[i - j]);
                }
                if (minCoin == Integer.MAX_VALUE) numOfAmount[i] = -1;
                else numOfAmount[i] = minCoin + 1;
            }
        }
        return numOfAmount[amount - 1];
    }

    /**
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     * <p>
     * For example,
     * Given:
     * s1 = "aabcc",
     * s2 = "dbbca",
     * <p>
     * When s3 = "aadbbcbcac", return true.
     * When s3 = "aadbbbaccc", return false.
     *
     * @param s1 first string
     * @param s2 second string
     * @param s3 target string
     * @return whether s3 is formed by the interleaving of s1 and s2.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = (s1 == null) ? 0 : s1.length();
        int n2 = (s2 == null) ? 0 : s2.length();
        int n3 = (s3 == null) ? 0 : s3.length();
        if (n3 != (n1 + n2)) return false;

        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[n1][n2] = true;

        for (int i = n1 - 1; i >= 0; i--) {
            dp[i][n2] = (dp[i + 1][n2] && s1.charAt(i) == s3.charAt(n2 + i));
        }

        for (int i = n2 - 1; i >= 0; i--) {
            dp[n1][i] = (dp[n1][i + 1] && s2.charAt(i) == s3.charAt(n1 + i));
        }

        for (int i1 = n1 - 1; i1 >= 0; i1--) {
            char c1 = s1.charAt(i1);
            for (int i2 = n2 - 1; i2 >= 0; i2--) {
                char c2 = s2.charAt(i2);
                char c3 = s3.charAt(i1 + i2);
                dp[i1][i2] |= ((c1 == c3 && dp[i1 + 1][i2]) || (c2 == c3 && dp[i1][i2 + 1]));
            }
        }

        return dp[0][0];
    }
}
