package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets
     * in the array which gives the sum of zero.
     * <p>
     * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
     * The solution set must not contain duplicate triplets.
     * https://leetcode.com/problems/3sum/
     *
     * @param nums an array S of n integers
     * @return unique triplets in the array which gives the sum of zero
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        int[] tempNums = new int[n];
        System.arraycopy(nums, 0, tempNums, 0, n);
        Arrays.sort(tempNums);
        List<List<Integer>> answerList = new ArrayList<>();
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (tempNums[i] > 0) break;
            else maxIndex++;
        }
        maxIndex = Integer.min(n - 2, maxIndex);
        for (int i = 0; i < maxIndex; i++) {
            if (i > 0 && tempNums[i] == tempNums[i - 1]) continue;
            for (int j = i + 1; j < n - 1; j++) {
                if (j != i + 1 && tempNums[j] == tempNums[j - 1]) continue;
                int sum_2 = -(tempNums[i] + tempNums[j]);
                if (sum_2 < 0) break;
                int index = Arrays.binarySearch(tempNums, j + 1, n, sum_2);
                if (index > 0) {
                    answerList.add(Arrays.asList(tempNums[i], tempNums[j], sum_2));
                }
            }
        }
        return answerList;
    }

    /**
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
     * Return the sum of the three integers. You may assume that each input would have exactly one solution.
     *
     * @param nums   Given an array S of n integers
     * @param target a given number
     * @return the closest sum to the given number
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null) return 0;
        int n = nums.length;
        if (n < 3) return 0;
        int closestSum = Integer.MAX_VALUE;
        int leastDiff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int k = findClosest(nums, target - nums[i] - nums[j], j + 1, n);
                int newSum = nums[i] + nums[j] + nums[k];
                if (newSum == target) return target;
                else {
                    int diff = Math.abs(newSum - target);
                    if (diff < leastDiff) {
                        leastDiff = diff;
                        closestSum = newSum;
                    }
                }
            }
        }
        return closestSum;
    }

    public int findClosest(int[] nums, int target, int startIndex, int endIndex) {
        if (nums[startIndex] >= target) return startIndex;
        if (nums[endIndex - 1] <= target) return endIndex - 1;
        if (endIndex - startIndex <= 1) {
            if (endIndex < nums.length) {
                return (Math.abs(target - nums[startIndex]) < Math.abs(target - nums[endIndex])) ?
                        startIndex : endIndex;
            } else return startIndex;
        }
        int midIndex = (startIndex + endIndex) / 2;
        if (nums[midIndex] == target) return midIndex;
        else if (nums[midIndex] > target) {
            if (midIndex > 1 && nums[midIndex - 1] < target) {
                return (Math.abs(nums[midIndex] - target) < Math.abs(nums[midIndex - 1] - target)) ? midIndex :
                        (midIndex - 1);
            }
            return findClosest(nums, target, startIndex, midIndex);
        } else {
            if (midIndex < nums.length - 1) {
                if (nums[midIndex + 1] > target) {
                    return (Math.abs(nums[midIndex] - target) < Math.abs(nums[midIndex + 1] - target)) ? midIndex :
                            (midIndex + 1);
                } else return findClosest(nums, target, midIndex + 1, endIndex);
            } else return midIndex;
        }
    }
}
