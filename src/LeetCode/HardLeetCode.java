package LeetCode;

import java.util.Arrays;

/**
 * Created by warn on 14/2/2016.
 */
public class HardLeetCode {
    public static void main(String[] args) {
        // put your codes here
        HardLeetCode test = new HardLeetCode();
        System.out.println(test.candy(new int[]{1}));
    }

    public int candy(int[] ratings) {
        int[] childCandy = new int[ratings.length];
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) childCandy[i] = childCandy[i - 1] + 1;
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && childCandy[i] <= childCandy[i + 1]) childCandy[i] =
                    childCandy[i + 1] + 1;
        }
        int total = 0;
        for (int i : childCandy) total += i;
        return total + ratings.length;
    }
}