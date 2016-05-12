package LeetCode;

import java.util.ArrayList;

/**
 * Created by warn on 4/5/2016.
 * Use to store puzzles with tag Dynamic programming
 */
public class TagDynamicProgramming {
    /**
     * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
     * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] *
     * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then
     * becomes adjacent.
     * <p>
     * Find the maximum coins you can collect by bursting the balloons wisely.
     * <p>
     * Note:
     * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
     * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
     * <p>
     * Example:
     * <p>
     * Given [3, 1, 5, 8]
     * <p>
     * Return 167
     * <p>
     * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
     *
     * @param nums a coin of balloons
     * @return maximum coins you can collect
     */
    public int maxCoins(int[] nums) {
        int[][] maxCoin = new int[nums.length + 2][nums.length + 2];
        return getMaxCoin(maxCoin, 0, nums.length + 1, nums);
    }

    private int getMaxCoin(int[][] maxCoin, int left, int right, int[] nums) {
        if (maxCoin[left][right] != 0) return maxCoin[left][right];
        else {
            int maxCoinValue = 0;
            for (int i = left + 1; i < right; i++) {
                maxCoinValue = Math.max(maxCoinValue, ((right == nums.length + 1) ? 1 : nums[right - 1]) *
                        ((left == 0) ? 1 : nums[left - 1]) * nums[i - 1] + getMaxCoin(maxCoin, left, i, nums) +
                        getMaxCoin(maxCoin, i, right, nums));
            }
            maxCoin[left][right] = maxCoinValue;
            return maxCoinValue;
        }
    }

    /**
     * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length
     * k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved.
     * Return an array of the k digits. You should try to optimize your time and space complexity.
     * <p>
     * Example 1:
     * nums1 = [3, 4, 6, 5]
     * nums2 = [9, 1, 2, 5, 8, 3]
     * k = 5
     * return [9, 8, 6, 5, 3]
     * <p>
     * Example 2:
     * nums1 = [6, 7]
     * nums2 = [6, 0, 4]
     * k = 5
     * return [6, 7, 6, 0, 4]
     * <p>
     * Example 3:
     * nums1 = [3, 9]
     * nums2 = [8, 9]
     * k = 3
     * return [9, 8, 9]
     *
     * @param nums1 array 1
     * @param nums2 array 2
     * @param k maximum number length
     * @return the maximum number
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if (nums1 == null && nums2 == null) return null;
        if (nums1 == null) nums1 = new int[0];
        else if (nums2 == null) nums2 = new int[0];
        int n1 = nums1.length, n2 = nums2.length;
        int[] maximum = new int[k];
        if (k == (n1 + n2)) maxNumber(maximum, 0, nums1, 0, nums2, 0);
        else findMaxNumber(maximum, 0, nums1, 0, nums2, 0);
        return maximum;
    }

    private void findMaxNumber(int[] maximum, int maxIndex, int[] nums1, int nums1Index, int[] nums2, int nums2Index){
        if (maxIndex == maximum.length) return;
        int remainLength = maximum.length - maxIndex;
        if (nums2.length - nums2Index + nums1.length - nums1Index == maximum.length - maxIndex) {
            maxNumber(maximum, maxIndex, nums1, nums1Index, nums2, nums2Index);
            return;
        }
        int[] tempMaximum = new int[remainLength], temp = new int[remainLength];
        int tmpMax = 0;
        ArrayList<Integer> tmpMaxIndex1 = new ArrayList<>(), tmpMaxIndex2 = new ArrayList<>();
        for (int i = nums1Index; i < nums1.length; i++) {
            if (nums2.length - nums2Index + nums1.length - i < remainLength) break;
            if (nums1[i] > tmpMax) {
                tmpMax = nums1[i];
                tmpMaxIndex1.clear();
                tmpMaxIndex1.add(i);
            } else if (nums1[i] == tmpMax) tmpMaxIndex1.add(i);
        }
        for (int i = nums2Index; i < nums2.length; i++) {
            if (nums2.length - i + nums1.length - nums1Index < remainLength) break;
            if (nums2[i] > tmpMax) {
                tmpMax = nums2[i];
                tmpMaxIndex2.clear();
                tmpMaxIndex1.clear();
                tmpMaxIndex2.add(i);
            } else if (nums2[i] == tmpMax) tmpMaxIndex2.add(i);
        }

        for (int i: tmpMaxIndex1) {
            temp[0] = nums1[i];
            findMaxNumber(temp, 1, nums1, i + 1, nums2, nums2Index);
            if (isBigger(temp, maximum, maxIndex)) System.arraycopy(temp, 0, maximum, maxIndex, remainLength);
        }

        for (int i: tmpMaxIndex2) {
            temp[0] = nums2[i];
            findMaxNumber(temp, 1, nums1, nums1Index, nums2, i + 1);
            if (isBigger(temp, maximum, maxIndex)) System.arraycopy(temp, 0, maximum, maxIndex, remainLength);
        }
    }

    private boolean isBigger(int[] nums1, int[] nums2, int nums2Index){
        for (int i = 0, n = nums1.length; i < n; i++) {
            if (nums1[i] < nums2[i + nums2Index]) return false;
            else if (nums1[i] > nums2[i]) return true;
        }
        return false;
    }

    private void maxNumber(int[] maximum, int maxIndex, int[] nums1, int nums1Index, int[] nums2, int nums2Index){
        for (int n = maximum.length; maxIndex < n; maxIndex++) {
            if (nums1Index >= nums1.length || nums2Index >= nums2.length) break;
            if (nums1[nums1Index] > nums2[nums2Index]) {
                maximum[maxIndex] = nums1[nums1Index++];
            } else {
                maximum[maxIndex] = nums2[nums2Index++];
            }
        }
        if (maxIndex != maximum.length) {
            if (nums1Index < nums1.length) {
                nums2 = nums1;
                nums2Index = nums1Index;
            }
            for (int n = nums2.length; nums2Index < n; nums2Index++){
                maximum[maxIndex++] = nums2[nums2Index];
            }
        }
    }
}
