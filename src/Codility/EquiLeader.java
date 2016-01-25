package Codility;

/**
 * Created by warn on 25/1/2016.
 * A non-empty zero-indexed array A consisting of N integers is given.
 * <p>
 * The leader of this array is the value that occurs in more than half of the elements of A.
 * <p>
 * An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 4
 * A[1] = 3
 * A[2] = 4
 * A[3] = 4
 * A[4] = 4
 * A[5] = 2
 * we can find two equi leaders:
 * <p>
 * 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
 * 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
 * The goal is to count the number of equi leaders.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int[] A); }
 * <p>
 * that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.
 * <p>
 * For example, given:
 * <p>
 * A[0] = 4
 * A[1] = 3
 * A[2] = 4
 * A[3] = 4
 * A[4] = 4
 * A[5] = 2
 * the function should return 2, as explained above.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class EquiLeader {
    public static void main(String[] args) {
        // put your codes here
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int n = A.length;
        int leaderValue = 0;
        int count = 0;
        int result = 0;

        for (int i = 0; i < n; i++) {
            if (count == 0) {
                count++;
                leaderValue = A[i];
            } else if (leaderValue == A[i]) {
                count++;
            } else {
                count--;
            }
        }
        if (count <= 0) {
            return result;
        }
        count = 0;

        for (int i = 0; i < n; i++) {
            if (A[i] == leaderValue) {
                count++;
            }
        }

        if (count <= n / 2) {
            return result;
        }

        int maxLeader = count;
        for (int i = 0; i < n; i++) {
            if (A[i] == leaderValue) count--;
            if (maxLeader - count > (i + 1) / 2 && count > (n - i - 1) / 2) result++;
        }
        return result;
    }
}