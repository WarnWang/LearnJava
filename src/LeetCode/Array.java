package LeetCode;

import java.util.Arrays;

/**
 * Created by warn on 16/3/2016.
 * Used to store information about array problems
 */
public class Array {
    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * You may assume that each input would have exactly one solution.
     * https://leetcode.com/problems/two-sum/
     *
     * @param nums   an array of integers
     * @param target a target number of the two sum
     * @return indices of the two numbers
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] tempNums = new int[n];
        System.arraycopy(nums, 0, tempNums, 0, n);
        Arrays.sort(tempNums);
        for (int i = 0; i < n - 1; i++) {
            int tempTarget = target - tempNums[i];
            int index = Arrays.binarySearch(tempNums, i + 1, n, tempTarget);
            if (index >= 0) {
                int[] indexResult = new int[2];
                int tempI = 0;
                for (int j = 0; j < n; j++) {
                    if (nums[j] == tempTarget || nums[j] == tempNums[i]) {
                        indexResult[tempI++] = j;
                        if (tempI == 2) break;
                    }
                }
                return indexResult;
            }
        }
        return null;
    }
}
