package Codility;

/**
 * Created by warn on 28/1/2016.
 * A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given an array A consisting of N integers, returns the maximum sum of any slice of A.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 3  A[1] = 2  A[2] = -6
 * A[3] = 4  A[4] = 0
 * the function should return 5 because:
 * <p>
 * (3, 4) is a slice of A that has sum 4,
 * (2, 2) is a slice of A that has sum −6,
 * (0, 1) is a slice of A that has sum 5,
 * no other slice of A has sum greater than (0, 1).
 * Assume that:
 * <p>
 * N is an integer within the range [1..1,000,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000];
 * the result will be an integer within the range [−2,147,483,648..2,147,483,647].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class MaxSliceSum {
    public static void main(String[] args) {
        // put your codes here
        MaxSliceSum test = new MaxSliceSum();
        System.out.println(test.solution(new int[]{3, 2, -3, 4, 0}));
        System.out.println(test.solution(new int[]{-1000000}));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int max = -1000000;
        int lastEnding = 0;
        for (int i = 0; i < A.length; i++) {
            max = Integer.max(max, lastEnding + A[i]);
            lastEnding = Integer.max(0, lastEnding + A[i]);
        }
        return max;
    }
}