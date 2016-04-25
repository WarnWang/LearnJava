package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by warn on 16/3/2016.
 * Used to store information about array problems
 */
public class TagArray {
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

    public List<List<Integer>> combinationSumRec(int[] candidates, int target) {
        if (candidates == null) return null;
        List<List<Integer>> uniqueCombinations = new ArrayList<>();
        Arrays.sort(candidates);
        int n = candidates.length;
        actionFunction(uniqueCombinations, candidates, new int[]{}, target);
        return uniqueCombinations;
    }

    public void actionFunction(List<List<Integer>> uniqueCombinations, int[] candidates, int[] path,
                               int target) {
        int index = 0;
        int n = path.length;
        if (n > 0) index = path[n - 1];
        for (; index < candidates.length; index++) {
            if (candidates[index] == target) {
                List<Integer> pathList = new ArrayList<>(n + 1);
                for (int aPath : path) pathList.add(candidates[aPath]);
                pathList.add(candidates[index]);
                uniqueCombinations.add(pathList);
            } else if (candidates[index] < target) {
                int[] newPath = new int[n + 1];
                System.arraycopy(path, 0, newPath, 0, n);
                newPath[n] = index;
                actionFunction(uniqueCombinations, candidates, newPath, target - candidates[index]);
            } else break;
        }
    }

    /**
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     *
     * @param nums unsorted array of integers
     * @return the longest increasing subsequence
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] LisInfo = new int[nums.length];
        int maxLength = 1;
        LisInfo[0] = nums[0];

        for (int i1 = 1; i1 < nums.length; i1++) {
            int lisLength = 0;
            for (int i = maxLength - 1; i >= 0; i--) {
                if (LisInfo[i] < nums[i1]) {
                    lisLength = i + 1;
                    maxLength = Integer.max(lisLength + 1, maxLength);
                    break;
                }
            }
            LisInfo[lisLength] = nums[i1];
        }
        return maxLength;
    }

    /**
     * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
     *
     * @param nums an unsorted array
     * @return whether an increasing subsequence of length 3 exists
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int[] formerTwo = new int[2];
        formerTwo[0] = nums[0];
        int i = 1;
        for (; i < nums.length; i++) {
            if (nums[i] > formerTwo[0]) {
                formerTwo[1] = nums[i];
                break;
            } else formerTwo[0] = nums[i];
        }
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > formerTwo[1]) return true;
            else if (nums[j] > formerTwo[0]) formerTwo[1] = nums[j];
            else formerTwo[0] = nums[j];
        }
        return false;
    }

    /**
     * A peak element is an element that is greater than its neighbors.
     * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
     * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
     * You may imagine that num[-1] = num[n] = -∞.
     * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
     *
     * @param nums an element array
     * @return the peak index
     */
    public int findPeakElement(int[] nums) {
        if (nums == null) return 0;
        int numsLen = nums.length;
        if (numsLen <= 1) return 0;
        return findPeakElement(nums, 0, nums.length - 1);
    }

    private int findPeakElement(int[] nums, int startIndex, int endIndex) {
        int mid = (startIndex + endIndex) / 2;
        if (startIndex > endIndex) return startIndex;
        else if (nums[startIndex] > nums[startIndex + 1]) return startIndex;
        else if (nums[endIndex] > nums[endIndex - 1]) return endIndex;
        else if (endIndex - startIndex < 2) {
            if (nums[startIndex] > nums[endIndex]) return startIndex;
            else return endIndex;
        } else if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) return mid;
        else if (nums[mid] > nums[mid - 1]) return findPeakElement(nums, mid + 1, endIndex);
        else return findPeakElement(nums, startIndex, mid - 1);
    }

    /**
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of
     * numbers.
     * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
     * order).
     * The replacement must be in-place, do not allocate extra memory.
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
     * column.
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     * @param nums array need to be rearranged
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int endIndex = nums.length - 1;
        for (int j = endIndex - 1; j >= 0; j--) {
            if (nums[endIndex] > nums[j]) {
                int nearestIndex = endIndex;
                int nearestValue = nums[endIndex] - nums[j];
                for (int k = j + 1; k <= endIndex; k++) {
                    if (nums[k] > nums[j] && nums[k] - nums[j] <= nearestValue && k < nearestIndex) {
                        nearestIndex = k;
                        nearestValue = nums[k] - nums[j];
                    }
                }
                nums[nearestIndex] = nums[j];
                nums[j] = nums[nearestIndex] + nearestValue;
                break;
            } else {
                for (int i = j + 1; i <= endIndex; i++) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    /**
     * Given a 2D board and a word, find if the word exists in the grid.
     * <p>
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
     * horizontally or vertically neighboring. The same letter cell may not be used more than once.
     * <p>
     * For example,
     * Given board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * word = "ABCCED", -> returns true,
     * word = "SEE", -> returns true,
     * word = "ABCB", -> returns false.
     *
     * @param board 2d board
     * @param word  if the word exists in the grid
     * @return exist or not
     */
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) return true;
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        char[] wordArray = word.toCharArray();
        boolean[][] explored = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == wordArray[0]) {
                    explored[i][j] = true;
                    if (exist(board, wordArray, 1, i, j, explored))
                        return true;
                    else explored[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, char[] word, int wordIndex, int x, int y, boolean[][] explored) {
        if (wordIndex == word.length) return true;
        if (x > 0 && !explored[x - 1][y] && board[x - 1][y] == word[wordIndex]) {
            explored[x - 1][y] = true;
            if (exist(board, word, wordIndex + 1, x - 1, y, explored)) return true;
            explored[x - 1][y] = false;
        }
        if (x < board.length - 1 && !explored[x + 1][y] && board[x + 1][y] == word[wordIndex]) {
            explored[x + 1][y] = true;
            if (exist(board, word, wordIndex + 1, x + 1, y, explored)) return true;
            explored[x + 1][y] = false;
        }
        if (y < board[0].length - 1 && !explored[x][y + 1] && board[x][y + 1] == word[wordIndex]) {
            explored[x][y + 1] = true;
            if (exist(board, word, wordIndex + 1, x, y + 1, explored)) return true;
            explored[x][y + 1] = false;
        }
        if (y > 0 && !explored[x][y - 1] && board[x][y - 1] == word[wordIndex]) {
            explored[x][y - 1] = true;
            if (exist(board, word, wordIndex + 1, x, y - 1, explored)) return true;
            explored[x][y - 1] = false;
        }
        return false;
    }

    /**
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are
     * adjacent, with the colors in the order red, white and blue.
     * <p>
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * <p>
     * Note:
     * You are not suppose to use the library's sort function for this problem.
     *
     * @param nums n objects
     */
    public void sortColors(int[] nums) {
        int nRed = 0, nWhite = 0;
        for (int num: nums) {
            switch (num){
                case 0:
                    nRed++;
                    break;
                case 1:
                    nWhite++;
                    break;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < nRed) nums[i] = 0;
            else if (i - nRed < nWhite) nums[i] = 1;
            else nums[i] = 2;
        }
    }
}
