package Codility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by warn on 1/2/2016.
 * You are given a non-empty zero-indexed array A consisting of N integers.
 * <p>
 * For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.
 * <p>
 * For example, consider integer N = 5 and array A such that:
 * <p>
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * For the following elements:
 * <p>
 * A[0] = 3, the non-divisors are: 2, 6,
 * A[1] = 1, the non-divisors are: 3, 2, 3, 6,
 * A[2] = 2, the non-divisors are: 3, 3, 6,
 * A[3] = 3, the non-divisors are: 2, 6,
 * A[6] = 6, there aren't any non-divisors.
 * Write a function:
 * <p>
 * class Solution { public int[] solution(int[] A); }
 * <p>
 * that, given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.
 * <p>
 * The sequence should be returned as:
 * <p>
 * a structure Results (in C), or
 * a vector of integers (in C++), or
 * a record Results (in Pascal), or
 * an array of integers (in any other programming language).
 * For example, given:
 * <p>
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * the function should return [2, 4, 3, 2, 0], as explained above.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..50,000];
 * each element of array A is an integer within the range [1..2 * N].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class CountNonDivisible {
    public static void main(String[] args) {
        // put your codes here
        CountNonDivisible test = new CountNonDivisible();
        test.solution2(new int[]{3, 1, 2, 3, 6});
        System.out.print(1);
    }

    public int[] solution(int[] A) {
        // write your code in Java SE 8
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int a = A[i];
                int b = A[j];
                if (a % b != 0) {
                    result[i]++;
                }
                if (b % a != 0) {
                    result[j]++;
                }
            }
        }
        return result;
    }

    public int[] solution2(int[] A) {
        // write your code in Java SE 8
        int[] result = new int[A.length];
        int[] flag = new int[2 * A.length];
        int[] dividerCounter = new int[2 * A.length];
        for (int aA : A) {
            flag[aA - 1]++;
            dividerCounter[aA - 1] = -1;
        }

        for (int aA : A) {
            if (dividerCounter[aA - 1] == -1) {
                dividerCounter[aA - 1] = 0;
                int a = (int) Math.sqrt(aA);
                for (int j = 1; j <= a; j++) {
                    if (aA % j == 0) {
                        if (aA != j * j) {
                            dividerCounter[aA - 1] += flag[j - 1];
                            dividerCounter[aA - 1] += flag[aA / j - 1];
                        } else {
                            dividerCounter[aA - 1] += flag[j - 1];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < A.length; i++) {
            result[i] = A.length - dividerCounter[A[i] - 1];
        }

        return result;
    }

    public int[] solution3(int[] A) {
        int result[] = new int[A.length];
        Map<Integer, Integer> numberCount = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            if (numberCount.containsKey(A[i])) {
                result[i] = numberCount.get(A[i]);
            }
            for (int j = i + 1; j < A.length; j++) {
                int a = A[i];
                int b = A[j];
                if (a % b != 0) {
                    result[i]++;
                }
                if (b % a != 0) {
                    result[j]++;
                }
            }
            numberCount.put(A[i], result[i]);
        }
        return result;
    }
}