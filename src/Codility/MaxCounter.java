package Codility;

import java.util.Arrays;

/**
 * Created by warn on 20/1/2016.
 */

public class MaxCounter {
    public static void main(String[] args) {
        // put your codes here
        MaxCounter test = new MaxCounter();
        int[] result = test.solution(5, new int[]{3, 4, 4, 6, 1, 4, 4});
        for (int i : result) {
            System.out.print(Integer.toString(i) + ", ");
        }
    }

    public int[] solution(int N, int[] A) {
        // write your code in Java SE 8
        int[] result = new int[N];
        int maxCounter = 0;
        int currentMaxCounter = 0;

        for (int i : A) {
            if (i >= N + 1) {
                currentMaxCounter = maxCounter;
            } else {
                result[i - 1] = (currentMaxCounter > result[i - 1] ? currentMaxCounter : result[i - 1]) + 1;
                maxCounter = result[i - 1] > maxCounter ? result[i - 1] : maxCounter;
            }
        }

        for (int i = 0; i < N; i++) {
            result[i] = currentMaxCounter > result[i] ? currentMaxCounter : result[i];
        }
        return result;
    }
}