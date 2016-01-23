package Codility;

import java.util.Arrays;

/**
 * Created by warn on 23/1/2016.
 */

public class Triangle {
    public static void main(String[] args) {
        // put your codes here
        Triangle test = new Triangle();
        System.out.println(test.solution(new int[]{1, 5, 2, 3, 4, 6}));
        System.out.println(test.solution(new int[]{10, 20, 50, 1, 80, 200}));
        System.out.println(test.solution(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8

        Arrays.sort(A);
        int result = 0;
        for (int i = 0; i < A.length - 2; i++) {
            if (A[i] > A[i + 2] - A[i + 1]) {
                result = 1;
                break;
            }
        }
        return result;
    }
}