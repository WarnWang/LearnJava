package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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

        int i = 0;
        int j = n - 1;
        while (j > i) {
            int sum = tempNums[i] + tempNums[j];
            if (sum > target) j--;
            else if (sum < target) i++;
            else {
                int[] indexResult = new int[2];
                int index = 0;
                boolean iFlag = false;
                boolean jFlag = false;
                for (int k = 0; k < n && index < 2; k++) {
                    if (nums[k] == tempNums[i] && !iFlag) {
                        indexResult[index++] = k;
                        iFlag = true;
                    } else if (nums[k] == tempNums[j] && !jFlag) {
                        indexResult[index++] = k;
                        jFlag = true;
                    }
                }
                if (index == 2) return indexResult;
                else break;
            }
        }
        return null;
    }

    public int[] twoSumOn(int[] nums, int target) {
        if (nums == null) return null;
        int n = nums.length;
        if (n < 2) return nums;

        int max = nums[0], min = max;
        for (int i : nums) {
            min = Integer.min(i, min);
            max = Integer.max(i, max);
        }
        int lowerBound = Integer.max(min, target - max);
        int upperBound = Integer.min(max, target - min);
        int flagArraySize = upperBound - lowerBound + 1;
        int[] flagArray = new int[flagArraySize];
        for (int i = 0; i < n; i++) {
            if (nums[i] <= upperBound && nums[i] >= lowerBound) {
                int nextExpectValue = target - nums[i] - lowerBound;
                System.out.println(Arrays.toString(flagArray));
                if (flagArray[nextExpectValue] == 0) {
                    flagArray[nextExpectValue] = i + 1;
                } else {
                    return new int[]{i, flagArray[nextExpectValue] - 1};
                }
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
     * <p>
     * Design information:
     * 1. If nums[i] > target / 4, which means the left sum of variables will have no chance to equal target
     * 2. If nums[j] < target / 4, which means all the value between i and j will be less than target / 4, then break
     * 3. if nums[p] > (target - nums[i] - nums[j]) / 2 and nums[q] < (target - nums[i] - nums[j]) / 2 break
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
        Arrays.sort(nums);
        int k;
        int limit1 = target >> 2;
        for (int i = 0; i < n - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] > limit1) break; // limit1
            for (int j = n - 1; j > i + 2; j--) {
                if (nums[j] < limit1) break; // limit2
                if (j != n - 1 && nums[j] == nums[j + 1]) continue;
                int p = i + 1;
                int q = j - 1;
                int sum2 = nums[i] + nums[j];
                int limit2 = (target - sum2) >> 1;
                while (p < q && nums[p] <= limit2 && nums[q] >= limit2) { // limit 3
                    int sum = sum2 + nums[p] + nums[q];
                    if (sum == target) {
                        answerList.add(Arrays.asList(nums[i], nums[p], nums[q], nums[j]));
                        for (k = p++; p < q && nums[k] == nums[p]; p++) ;
                        for (k = q--; q > p && nums[k] == nums[q]; q--) ;
                    } else if (sum > target) q--;
                    else p++;
                }
            }
        }
        return answerList;
    }

    /**
     * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the
     * candidate numbers sums to T.
     * <p>
     * The same repeated number may be chosen from C unlimited number of times.
     * <p>
     * Notes:
     * 1. All numbers (including target) will be positive integers.
     * 2. Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     * 3. The solution set must not contain duplicate combinations.
     *
     * @param candidates candidate numbers
     * @param target     target number
     * @return unique combinations
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null) return null;
        List<List<Integer>> uniqueCombinations = new ArrayList<>();
        Stack<List<Integer>> stateToExplore = new Stack<>();
        stateToExplore.add(new ArrayList<>());
        Arrays.sort(candidates);
        int n = candidates.length;
        while (!stateToExplore.isEmpty()) {
            List<Integer> frontier = stateToExplore.pop();
            int frontSum = 0;
            int lastIndex = 0;
            for (int i : frontier) {
                frontSum += candidates[i];
                lastIndex = i;
            }
            for (int i = lastIndex; i < n; i++) {
                if (candidates[i] == (target - frontSum)) {
                    List<Integer> result = new ArrayList<>();
                    for (Integer aFrontier : frontier) {
                        int j = aFrontier;
                        result.add(candidates[j]);
                    }
                    result.add(candidates[i]);
                    uniqueCombinations.add(result);
                } else if (candidates[i] < (target - frontSum)) {
                    List<Integer> tempFrontier = new ArrayList<>();
                    tempFrontier.addAll(frontier);
                    tempFrontier.add(i);
                    stateToExplore.add(tempFrontier);
                } else break;
            }
        }
        return uniqueCombinations;
    }
}
