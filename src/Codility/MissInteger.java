package Codility;

/**
 * Created by warn on 20/1/2016.
 */
public class MissInteger {
    public static void main(String[] args) {
        // put your codes here
        MissInteger test = new MissInteger();
        System.out.print(test.solution(new int[]{1}));
    }

    public int solution(int[] A) {
        int result = A.length;
        int[] temp = new int[result];

        for (int i : A) {
            if (i > 0 && i <= result) {
                temp[i - 1] += 1;
            }
        }

        for (int i = 0; i < result; i++) {
            if (temp[i] == 0) {
                return i + 1;
            }
        }
        return result + 1;
    }
}