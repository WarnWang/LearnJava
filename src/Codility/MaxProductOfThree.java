package Codility;

/**
 * Created by warn on 22/1/2016.
 */
public class MaxProductOfThree {
    public static void main(String[] args) {
        // put your codes here
        MaxProductOfThree test = new MaxProductOfThree();
        System.out.println(test.solution(new int[]{-6, 1, 2, -2, -5, -6}));
    }

    public int solution(int[] A) {
        int[] maxValue = new int[]{-1001, -1001, -1001, 1001, 1001};

        for (int i : A) {
            if (i > maxValue[0]) {
                if (i > maxValue[1]) {
                    if (i > maxValue[2]) {
                        maxValue[0] = maxValue[1];
                        maxValue[1] = maxValue[2];
                        maxValue[2] = i;
                    } else {
                        maxValue[0] = maxValue[1];
                        maxValue[1] = i;
                    }
                } else {
                    maxValue[0] = i;
                }
            }
            if (i < maxValue[4]) {
                if (i < maxValue[3]) {
                    maxValue[4] = maxValue[3];
                    maxValue[3] = i;
                } else {
                    maxValue[4] = i;
                }
            }
        }
        int result1 = maxValue[2] * maxValue[1] * maxValue[0];
        int result2 = maxValue[2] * maxValue[3] * maxValue[4];
        return result1 > result2 ? result1 : result2;
    }
}