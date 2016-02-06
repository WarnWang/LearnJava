package Codility;

/**
 * Created by warn on 6/2/2016.
 * You are given integers K, M and a non-empty zero-indexed array A consisting of N integers. Every element of the array is not greater than M.
 * <p>
 * You should divide this array into K blocks of consecutive elements. The size of the block is any integer between 0 and N. Every element of the array should belong to some block.
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
 * that, given integers K, M and a non-empty zero-indexed array A consisting of N integers, returns the minimal large sum.
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
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class MinMaxDivision {
    public static void main(String[] args) {
        // put your codes here
    }

    public int solution(int K, int M, int[] A) {
        // write your code in Java SE 8
        int maxK = Integer.min(K, A.length);
        int minimalLargeSum = 0;
        int lastResult = 0;
        int sum = 0;
        for (int aA : A) sum += aA;
        for (int i = 1; i <= maxK; i++) {
        }
        return minimalLargeSum;
    }
}