package LeetCode;

import java.util.*;

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
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && tempNums[i] == tempNums[i - 1]) continue;
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                if (j > i + 1 && tempNums[j] == tempNums[j - 1]) {
                    j++;
                    continue;
                } else if (k < n - 1 && tempNums[k] == tempNums[k + 1]) {
                    k--;
                    continue;
                }
                int sum = tempNums[i] + tempNums[j] + tempNums[k];
                if (sum == 0) {
                    answerList.add(Arrays.asList(tempNums[i], tempNums[j], tempNums[k]));
                    j++;
                    k--;
                } else if (sum > 0) k--;
                else j++;
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
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int newSum = nums[i] + nums[j] + nums[k];
                if (newSum == target) return target;
                else {
                    int diff = Math.abs(newSum - target);
                    if (diff < leastDiff) {
                        leastDiff = diff;
                        closestSum = newSum;
                    }
                    if (newSum > target) k--;
                    else j++;
                }
            }
        }
        return closestSum;
    }

    /**
     * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find
     * all unique quadruplets in the array which gives the sum of target.
     * <p>
     * Note:
     * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
     * The solution set must not contain duplicate quadruplets.
     *
     * @param nums   an array S of n integers
     * @param target the target value
     * @return all unique quadruplets in the array which gives the sum of target
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> answerList = new ArrayList<>();
        if (nums == null) return answerList;
        int n = nums.length;
        if (n < 4) return answerList;
        Set<List<Integer>> answerSet = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; ) {
            for (int j = i + 1; j < n - 2; ) {
                int p = j + 1;
                int q = n - 1;
                while (p < q) {
                    int sum = nums[i] + nums[j] + nums[p] + nums[q];
                    if (sum == target) {
                        answerSet.add(Arrays.asList(nums[i], nums[j], nums[p], nums[q]));
//                        int k = j + 1;
//                        while (k < n - 3){
//                            if (nums[k] == nums[j]) k++;
//                            else break;
//                        }
//                        j = k;
                    } else if (sum > target) q--;
                    else p++;
                }
//                int k = j + 1;
//                while (k < n - 3){
//                    if (nums[k] == nums[j]) k++;
//                    else break;
//                }
//                j = k;
            }
//            int k = i + 1;
//            while (k < n - 3){
//                if (nums[k] == nums[i]) k++;
//                else break;
//            }
//            i = k;
        }
        answerList.addAll(answerSet);
        return answerList;
    }
}
