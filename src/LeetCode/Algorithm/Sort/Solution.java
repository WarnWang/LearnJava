package LeetCode.Algorithm.Sort;

import java.util.Arrays;

/**
 * Created by warn on 7/9/2016.
 * Solve problem with tag sort
 */
public class Solution {

    /**
     * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
     * <p>
     * Try to solve it in linear time/space.
     * <p>
     * Return 0 if the array contains less than 2 elements.
     * <p>
     * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
     *
     * @param nums an unsorted array
     * @return find the maximum difference between the successive elements
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int maxNum = nums[0];
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            maxNum = Math.max(nums[i], maxNum);
        }
        int module = 1;
        while (maxNum / module > 0) {
            int[] count = new int[10];
            int[] temp = new int[n];
            for (int i : nums) {
                count[(i % (module * 10)) / module]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int j = n - 1; j >= 0; j--) {
                int i = nums[j];
                temp[--count[(i % (module * 10)) / module]] = i;
            }
            nums = temp;
            module *= 10;
        }

        System.out.println(Arrays.toString(nums));
        int maximumGap = 0;

        for (int i = 1; i < n; i++) {
            maximumGap = Math.max(maximumGap, Math.abs(nums[i] - nums[i - 1]));
        }
        return maximumGap;
    }
}
