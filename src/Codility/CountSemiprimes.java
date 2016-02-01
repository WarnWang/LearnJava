package Codility;

/**
 * Created by warn on 1/2/2016.
 * A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.
 * <p>
 * A semiprime is a natural number that is the product of two (not necessarily distinct) prime numbers. The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.
 * <p>
 * You are given two non-empty zero-indexed arrays P and Q, each consisting of M integers. These arrays represent queries about the number of semiprimes within specified ranges.
 * <p>
 * Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ≤ P[K] ≤ Q[K] ≤ N.
 * <p>
 * For example, consider an integer N = 26 and arrays P, Q such that:
 * <p>
 * P[0] = 1    Q[0] = 26
 * P[1] = 4    Q[1] = 10
 * P[2] = 16   Q[2] = 20
 * The number of semiprimes within each of these ranges is as follows:
 * <p>
 * (1, 26) is 10,
 * (4, 10) is 4,
 * (16, 20) is 0.
 * Write a function:
 * <p>
 * class Solution { public int[] solution(int N, int[] P, int[] Q); }
 * <p>
 * that, given an integer N and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M elements specifying the consecutive answers to all the queries.
 * <p>
 * For example, given an integer N = 26 and arrays P, Q such that:
 * <p>
 * P[0] = 1    Q[0] = 26
 * P[1] = 4    Q[1] = 10
 * P[2] = 16   Q[2] = 20
 * the function should return the values [10, 4, 0], as explained above.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..50,000];
 * M is an integer within the range [1..30,000];
 * each element of arrays P, Q is an integer within the range [1..N];
 * P[i] ≤ Q[i].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N*log(log(N))+M);
 * expected worst-case space complexity is O(N+M), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class CountSemiprimes {
    public static void main(String[] args) {
        // put your codes here
        CountSemiprimes test = new CountSemiprimes();
        int[] result = test.solution(26, new int[]{1, 4, 16}, new int[]{26, 10, 20});
        for (int i : result) System.out.println(i);
    }

    public int[] solution(int N, int[] P, int[] Q) {
        // write your code in Java SE 8
        if (N <= 3) return new int[P.length];
        int[] prefixSum = new int[N];
        int[] flag = new int[N];

        int[] result = new int[P.length];

        for (int i = 4; i <= N; i++) {
            int k = i, count = 0, upBound = i / 2;
            if (flag[i - 1] != 0) {
                prefixSum[i - 1] = prefixSum[i - 2];
                continue;
            }
            for (int j = 2; j <= upBound; j++) {
                if (k % j == 0) {
                    count++;
                    k /= j;
                    upBound = k;
                    j--;
                }
                if (count > 2) break;
            }
            if (count == 2) {
                prefixSum[i - 1] = prefixSum[i - 2] + 1;
                for (int j = i - 1; j < N; j += i) {
                    flag[j]++;
                }
            } else prefixSum[i - 1] = prefixSum[i - 2];
        }

        for (int i = 0; i < P.length; i++) {
            result[i] = prefixSum[Q[i] - 1] - prefixSum[P[i] > 2 ? P[i] - 2 : P[i] - 1];
        }
        return result;
    }
}