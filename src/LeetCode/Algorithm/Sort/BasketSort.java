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
        for (int num : nums) {
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

    /**
     * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to
     * compute the researcher's h-index.
     * <p>
     * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at
     * least h citations each, and the other N − h papers have no more than h citations each."
     * <p>
     * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of
     * them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3
     * citations each and the remaining two with no more than 3 citations each, his h-index is 3.
     * <p>
     * Note: If there are several possible values for h, the maximum one is taken as the h-index.
     *
     * @param citations an array of index
     * @return the h-index of this researcher
     */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int n = citations.length;
        int[] basket = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int num = citations[i];
            if (num > n) basket[n]++;
            else basket[num]++;
        }
        for (int i = n; i > 0; i--){
            if (basket[i] >= i) return i;
            basket[i - 1] += basket[i];
        }
        return 0;
    }
}
