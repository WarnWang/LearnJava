package Codility;

import java.util.Arrays;

/**
 * Created by warn on 23/1/2016.
 */
public class NumberOfDiscIntersections {
    public static void main(String[] args) {
        // put your codes here
        NumberOfDiscIntersections test = new NumberOfDiscIntersections();
        System.out.print(test.solution(new int[]{1, 1, 1}));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int result = 0, j = 0;
        int[] rangeUpper = new int[A.length];
        int[] rangeLower = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            rangeLower[i] = i - A[i];
            rangeUpper[i] = i + A[i];
        }

        Arrays.sort(rangeLower);
        Arrays.sort(rangeUpper);

        for (int i = 0; i < A.length - 1; i++) {
            while (j < A.length && rangeUpper[i] >= rangeLower[j]) {
                j++;
            }

            result += j - i - 1;
            if (result > 10000000) {
                result = -1;
                break;
            }
        }
        return result;
    }
}