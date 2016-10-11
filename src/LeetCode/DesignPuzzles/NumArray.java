package LeetCode.DesignPuzzles;

/**
 * Created by warn on 11/10/2016.
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * <p>
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * <p>
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 */
public class NumArray {

    private int[] partialSum;

    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) partialSum = new int[0];
        else {
            partialSum = new int[nums.length];
            partialSum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                partialSum[i] = partialSum[i - 1] + nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        if (partialSum.length == 0) return 0;
        if (j >= partialSum.length) j = partialSum.length - 1;
        if (i <= 0) return partialSum[j];
        else if (i > j) return 0;
        else return partialSum[j] - partialSum[i - 1];
    }
}
