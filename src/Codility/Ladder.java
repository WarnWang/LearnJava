package Codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by warn on 5/2/2016.You have to climb up a ladder. The ladder has exactly N rungs, numbered from 1 to N. With each step, you can ascend by one or two rungs. More precisely:
 * <p>
 * with your first step you can stand on rung 1 or 2,
 * if you are on rung K, you can move to rungs K + 1 or K + 2,
 * finally you have to stand on rung N.
 * Your task is to count the number of different ways of climbing to the top of the ladder.
 * <p>
 * For example, given N = 4, you have five different ways of climbing, ascending by:
 * <p>
 * 1, 1, 1 and 1 rung,
 * 1, 1 and 2 rungs,
 * 1, 2 and 1 rung,
 * 2, 1 and 1 rungs, and
 * 2 and 2 rungs.
 * Given N = 5, you have eight different ways of climbing, ascending by:
 * <p>
 * 1, 1, 1, 1 and 1 rung,
 * 1, 1, 1 and 2 rungs,
 * 1, 1, 2 and 1 rung,
 * 1, 2, 1 and 1 rung,
 * 1, 2 and 2 rungs,
 * 2, 1, 1 and 1 rungs,
 * 2, 1 and 2 rungs, and
 * 2, 2 and 1 rung.
 * The number of different ways can be very large, so it is sufficient to return the result modulo 2P, for a given integer P.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int[] solution(int[] A, int[] B); }
 * <p>
 * that, given two non-empty zero-indexed arrays A and B of L integers, returns an array consisting of L integers specifying the consecutive answers; position I should contain the number of different ways of climbing the ladder with A[I] rungs modulo 2B[I].
 * <p>
 * For example, given L = 5 and:
 * <p>
 * A[0] = 4   B[0] = 3
 * A[1] = 4   B[1] = 2
 * A[2] = 5   B[2] = 4
 * A[3] = 5   B[3] = 3
 * A[4] = 1   B[4] = 1
 * the function should return the sequence [5, 1, 8, 0, 1], as explained above.
 * <p>
 * Assume that:
 * <p>
 * L is an integer within the range [1..30,000];
 * each element of array A is an integer within the range [1..L];
 * each element of array B is an integer within the range [1..30].
 * Complexity:
 * <p>
 * expected worst-case time complexity is O(L);
 * expected worst-case space complexity is O(L), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class Ladder {
    List<Integer> Fibonacci = new ArrayList<>(Arrays.asList(1, 1));

    public static void main(String[] args) {
        // put your codes here
    }

    public int[] solution(int[] A, int[] B) {
        // write your code in Java SE 8
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            setFibonacci(A[i]);
            System.out.println("objects" + Fibonacci);
            result[i] = Fibonacci.get(A[i]) % (int) Math.pow(2, B[i]);
        }
        return result;
    }

    public void setFibonacci(int n) {
        int arraySize = Fibonacci.size();
        int f1 = Fibonacci.get(arraySize - 2);
        int f2 = Fibonacci.get(arraySize - 1);
        while (arraySize <= n + 1) {
            int temp = f1;
            f1 = f2;
            f2 += temp;
            arraySize++;
            Fibonacci.add(f2);
        }
    }
}