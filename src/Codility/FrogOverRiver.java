package Codility;

import java.util.Arrays;

/**
 * Created by warn on 20/1/2016.
 */
public class FrogOverRiver {
    public static void main(String[] args) {
        Solution1 test = new Solution1();
        System.out.print(test.solution(5, new int[]{1, 3, 1, 4, 2, 3, 5, 4}));
    }
}


class Solution1 {
    public int solution(int X, int[] A) {
        int[] riverWidth = new int[X];
        Arrays.fill(riverWidth, 0);
        int temp = 0;

        for (int i = 0; i < A.length; i++) {
            if (riverWidth[A[i] - 1] == A[i]) {
                continue;
            } else {
                riverWidth[A[i] - 1] = A[i];
                temp += 1;
            }

            if (temp >= X) {
                return i;
            }
        }

        return -1;
    }
}
