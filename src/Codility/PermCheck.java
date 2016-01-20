package Codility;

/**
 * Created by warn on 20/1/2016.
 */
public class PermCheck {
    public static void main(String[] args) {
        Solution test = new Solution();
        int[] test_array = {1, 2, 3, 4, 6};
        System.out.print(test.solution(test_array));
    }
}


class Solution {
    public int solution(int[] A) {
        int[] temp = new int[A.length];
        int result = 1;
        for (int i = 0; i < A.length; i++) {
            temp[i] = i + 1;
        }
        for (int i : A) {
            if (i > temp.length) {
                result = 0;
                break;
            }
            temp[i - 1] -= i;
            if (temp[i - 1] < 0) {
                result = 0;
                break;
            }
        }

        if (result == 1) {
            for (int i : temp) {
                if (i != 0) {
                    result = 0;
                    break;
                }
            }
        }

        return result;
    }
}