package Codility;

import java.util.Random;

/**
 * Created by warn on 6/2/2016.
 * You are given integers K, M and a non-empty zero-indexed array A consisting of N integers. Every element of the
 * array is not greater than M.
 * <p>
 * You should divide this array into K blocks of consecutive elements. The size of the block is any integer between 0
 * and N. Every element of the array should belong to some block.
 * <p>
 * The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.
 * <p>
 * The large sum is the maximal sum of any block.
 * <p>
 * For example, you are given integers K = 3, M = 5 and array A such that:
 * <p>
 * A[0] = 2
 * A[1] = 1
 * A[2] = 5
 * A[3] = 1
 * A[4] = 2
 * A[5] = 2
 * A[6] = 2
 * The array can be divided, for example, into the following blocks:
 * <p>
 * [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
 * [2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
 * [2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
 * [2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
 * The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int K, int M, int[] A); }
 * <p>
 * that, given integers K, M and a non-empty zero-indexed array A consisting of N integers, returns the minimal large
 * sum.
 * <p>
 * For example, given K = 3, M = 5 and array A such that:
 * <p>
 * A[0] = 2
 * A[1] = 1
 * A[2] = 5
 * A[3] = 1
 * A[4] = 2
 * A[5] = 2
 * A[6] = 2
 * the function should return 6, as explained above.
 * <p>
 * Assume that:
 * <p>
 * N and K are integers within the range [1..100,000];
 * M is an integer within the range [0..10,000];
 * each element of array A is an integer within the range [0..M].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N*log(N+M));
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input
 * arguments).
 * Elements of input arrays can be modified.
 */
public class MinMaxDivision {
    public static void main(String[] args) {
        // put your codes here
        MinMaxDivision test = new MinMaxDivision();
//        System.out.println(test.solution(3, 5, new int[]{2, 1, 5, 1, 2, 2, 2, 2}));
//        System.out.println(test.solution2(3, 5, new int[]{2, 1, 5, 1, 2, 2, 2, 2}));
        System.out.println(test.solution2(2, 5, new int[]{5, 3}));
        int[] a = new int[100];
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            a[i] = random.nextInt(10);
//            i += random.nextInt(10);
        }
        System.out.println(test.solution2(9, 10, a));
        System.out.println(test.solution(9, 10, a));
    }

    public int solution(int K, int M, int[] A) {
        // write your code in Java SE 8
        if (K == 0 || A == null || A.length == 0) return 0;
        if (K >= A.length) return M;
        int[] preSum = new int[A.length];
        preSum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            preSum[i] = preSum[i - 1] + A[i];
        }
        return dynamicFindSolution(preSum, A, K, 0, A.length);
    }

    public int dynamicFindSolution(int[] preSum, int[] origin, int K, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return 0;
        if (K == 1) return getSubSum(preSum, origin, startIndex, endIndex);
        int minSumValue = -1;
        for (int i = startIndex; i < endIndex; i++) {
            int previousSum = getSubSum(preSum, origin, startIndex, i + 1);
            if (minSumValue != -1) {
                if (previousSum >= minSumValue) break;
                else {
                    int nextLevelSum = dynamicFindSolution(preSum, origin, K - 1, i + 1, endIndex);
                    minSumValue = Integer.min(minSumValue, Integer.max(previousSum, nextLevelSum));
                }
            } else minSumValue = Integer.max(previousSum, dynamicFindSolution(preSum, origin, K - 1, i + 1, endIndex));
//            if (K == 3) System.out.println(minSumValue);
        }
        return minSumValue;
    }

    public int getSubSum(int[] preSum, int[] origin, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return 0;
        else if (startIndex == endIndex - 1) return origin[startIndex];
        else if (startIndex == 0) return preSum[endIndex - 1];
        else return preSum[endIndex - 1] - preSum[startIndex] + origin[startIndex];
    }

    /**
     * https://codesays.com/2014/solution-to-min-max-division-by-codility/
     */
    public int solution2(int K, int M, int[] A) {
        int lowerBound = Integer.MIN_VALUE;
        int upperBound = 0;
        for (int a : A) {
            upperBound += a;
            lowerBound = Integer.max(lowerBound, a);
        }
        if (K == 1) return upperBound;
        if (K >= A.length) return lowerBound;
        int minMaxDivision = upperBound;
        while (lowerBound <= upperBound) {
            int midMaxBound = (upperBound + lowerBound) / 2;
            if (getBlocks(A, midMaxBound) <= K) {
                upperBound = midMaxBound - 1;
                minMaxDivision = midMaxBound;
            } else lowerBound = midMaxBound + 1;
        }
        return minMaxDivision;
    }

    public int getBlocks(int[] A, int maxBound) {
        int blockingNumber = 1;
        int tempSum = 0;
        for (int aA : A) {
            if (tempSum + aA <= maxBound) tempSum += aA;
            else {
                blockingNumber++;
                tempSum = aA;
            }
        }
        return blockingNumber;
    }
}