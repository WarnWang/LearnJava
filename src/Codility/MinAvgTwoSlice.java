package Codility;

/**
 * Created by warn on 22/1/2016.
 */
public class MinAvgTwoSlice {
    public static void main(String[] args) {
        // put your codes here
        MinAvgTwoSlice test = new MinAvgTwoSlice();
        System.out.println(test.solution(new int[]{4, 2, 2, 5, 1, 5, 8}));
        System.out.println(test.solution(new int[]{1, -1}));
        System.out.println(test.solution(new int[]{-3, -5, -8, -4, -10, -20}));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int minAverageSum = 10001 * 6;
        int result = 0;

        for (int i = 0; i < A.length - 1; i++) {
            int sum = A[i] + A[i + 1];
            if (minAverageSum > sum * 3) {
                minAverageSum = sum * 3;
                result = i;
            }

            if (i < A.length - 2) {
                sum = A[i] + A[i + 1] + A[i + 2];
                if (minAverageSum > sum * 2) {
                    minAverageSum = sum * 2;
                    result = i;
                }
            }
        }
        return result;
    }
}