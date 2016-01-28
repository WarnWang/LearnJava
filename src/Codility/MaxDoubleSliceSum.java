package Codility;

/**
 * Created by warn on 27/1/2016.
 * A non-empty zero-indexed array A consisting of N integers is given.
 * <p>
 * A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
 * <p>
 * The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
 * <p>
 * For example, array A such that:
 * <p>
 * A[0] = 3
 * A[1] = 2
 * A[2] = 6
 * A[3] = -1
 * A[4] = 4
 * A[5] = 5
 * A[6] = -1
 * A[7] = 2
 * contains the following example double slices:
 * <p>
 * double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
 * double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
 * double slice (3, 4, 5), sum is 0.
 * The goal is to find the maximal sum of any double slice.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a non-empty zero-indexed array A consisting of N integers, returns the maximal sum of any double slice.
 * <p>
 * For example, given:
 * <p>
 * A[0] = 3
 * A[1] = 2
 * A[2] = 6
 * A[3] = -1
 * A[4] = 4
 * A[5] = 5
 * A[6] = -1
 * A[7] = 2
 * the function should return 17, because no double slice of array A has a sum of greater than 17.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [3..100,000];
 * each element of array A is an integer within the range [−10,000..10,000].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class MaxDoubleSliceSum {
    public static void main(String[] args) {
        // put your codes here
        MaxDoubleSliceSum test = new MaxDoubleSliceSum();
        System.out.println(test.solution(new int[]{5, 0, 1, 0, 5}));
        System.out.println(test.solution(new int[]{3, 2, 6, -1, 4, 5, -1, 2}));
        System.out.println(test.solution(new int[]{30, 10, -5, -2, 0}));
        System.out.println(test.solution(new int[]{0, 10, -5, -2, 0}));
        System.out.println(test.solution(new int[]{5, 17, 0, 3}));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length < 3) return 0;
        int maxSlice = 0;
        int[] orderMaxSlice = new int[A.length];
        int[] turnMaxSlice = new int[A.length];
        for (int i = 1; i < A.length - 1; i++) {
            if (i != 1) {
                orderMaxSlice[i] = Integer.max(Integer.max(orderMaxSlice[i - 1], 0) + A[i - 1], 0);
                turnMaxSlice[A.length - i - 1] = Integer.max(Integer.max(turnMaxSlice[A.length - i], 0)
                        + A[A.length - i], 0);
            }
        }

        for (int i = 1; i < orderMaxSlice.length - 1; i++) {
            maxSlice = Integer.max(orderMaxSlice[i] + turnMaxSlice[i], maxSlice);
        }
        return maxSlice;
    }
}