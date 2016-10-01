package LeetCode.Algorithm.DivideAndConquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by warn on 1/10/2016.
 * With tag divide and conquer
 */
public class Solution {
    /**
     * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
     * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
     * <p>
     * Note:
     * A naive algorithm of O(n2) is trivial. You MUST do better than that.
     * <p>
     * Example:
     * Given nums = [-2, 5, -1], lower = -2, upper = 2,
     * Return 3.
     * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
     *
     * @param nums  an integer array
     * @param lower lower bound
     * @param upper upper bound
     * @return the number of sum in the nums
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        long sumResult;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sumResult = 0;
            for (int j = i; j < nums.length; j++) {
                sumResult += nums[j];
                if (sumResult >= lower && sumResult <= upper) count++;
            }
        }
        return count;
    }

    /**
     * You are given an integer array nums and you have to return a new counts array. The counts array has the property
     * where counts[i] is the number of smaller elements to the right of nums[i].
     * <p>
     * Example:
     * <p>
     * Given nums = [5, 2, 6, 1]
     * <p>
     * To the right of 5 there are 2 smaller elements (2 and 1).
     * To the right of 2 there is only 1 smaller element (1).
     * To the right of 6 there is 1 smaller element (1).
     * To the right of 1 there is 0 smaller element.
     * Return the array [2, 1, 1, 0].
     *
     * @param nums an integer array
     * @return a list of number array
     */
    public List<Integer> countSmaller(int[] nums) {
        ArrayList<Integer> smallerNum = new ArrayList<>();
        if (nums == null || nums.length == 0) return smallerNum;
        smallerNum = new ArrayList<>(nums.length);
        ArrayList<Integer> numsSort = new ArrayList<>(nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            smallerNum.add(binaryInsert(numsSort, nums[i]));
        }
        Collections.reverse(smallerNum);
        return smallerNum;
    }

    private int binaryInsert(ArrayList<Integer> numsSort, int target) {
        int index = 0;
        if (numsSort.isEmpty()) {
            numsSort.add(target);
        } else {
            int start = 0, end = numsSort.size();
            while (start < end) {
                if (target <= numsSort.get(start)) {
                    end = start;
                } else if (target > numsSort.get(end - 1)) {
                    start = end;
                } else {
                    int middle = (start + end) / 2;
                    int middleNum = numsSort.get(middle);
                    if (middleNum >= target) end = middle;
                    else start = middle + 1;
                }
            }
            index = start;
            numsSort.add(index, target);
        }
        return index;
    }
}
