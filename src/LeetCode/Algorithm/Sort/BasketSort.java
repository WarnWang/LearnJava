package LeetCode.Algorithm.Sort;

import java.util.Arrays;

/**
 * Created by warn on 5/6/2016.
 * Solve some basket sort puzzles
 */
public class BasketSort {

    /**
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are
     * adjacent, with the colors in the order red, white and blue.
     * <p>
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     *
     * @param nums an array of colors
     */
    public void sortColors(int[] nums) {
        if (nums == null) return;
        int redNum = 0, whiteNum = 0;
        for (int num: nums) {
            switch (num) {
                case 0:
                    redNum++;
                    break;
                case 1:
                    whiteNum++;
            }
        }
        Arrays.fill(nums, 0, redNum, 0);
        Arrays.fill(nums, redNum, whiteNum + redNum, 1);
        Arrays.fill(nums, whiteNum + redNum, nums.length, 2);
    }
}
