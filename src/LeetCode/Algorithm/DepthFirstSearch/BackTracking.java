package LeetCode.Algorithm.DepthFirstSearch;

import java.util.*;

/**
 * Created by warn on 13/6/2016.
 * Store solution to test with backtracking
 */
public class BackTracking {
    boolean findPath;
    private HashSet<String> contains;
    private int combinationNum = 0;

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        if (k > n) return combinations;
        combine(n, k, new int[k], 0, combinations);
        return combinations;
    }

    private void combine(int n, int k, int[] path, int index, List<List<Integer>> combinations) {
        if (index == k) {
            ArrayList<Integer> result = new ArrayList<>();
            for (int num : path) result.add(num);
            combinations.add(result);
        } else {
            int start, end;
            if (index > 0) start = path[index - 1] + 1;
            else start = 1;
            end = n - k + 2 + index;
            for (int i = start; i < end; i++) {
                path[index] = i;
                combine(n, k, path, index + 1, combinations);
            }
        }
    }

    /**
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
     * <p>
     * Note: The solution set must not contain duplicate subsets.
     * <p>
     * For example,
     * If nums = [1,2,2], a solution is:
     * <p>
     * [
     * [2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []
     * ]
     *
     * @param nums a collection of integers
     * @return all possible subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        if (nums != null && nums.length > 0) {
            contains = new HashSet<>();
            getSubSetsWithOutDup(nums, 0, new Integer[nums.length], 0, result);
        }
        return result;
    }

    private void getSubSetsWithOutDup(int[] nums, int index, Integer[] path, int pathSize, List<List<Integer>> result) {
        for (int i = index; i < nums.length; i++) {
            path[pathSize] = nums[i];
            Integer[] subSet = Arrays.copyOf(path, pathSize + 1);
            Arrays.sort(subSet);
            String subSetString = Arrays.toString(subSet);
            if (!contains.contains(subSetString)) {
                contains.add(subSetString);
                result.add(Arrays.asList(subSet));
            }
            getSubSetsWithOutDup(nums, i + 1, path, pathSize + 1, result);
        }
    }

    /**
     * The gray code is a binary numeral system where two successive values differ in only one bit.
     * <p>
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray
     * code. A gray code sequence must begin with 0.
     * <p>
     * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
     * <p>
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     * Note:
     * For a given n, a gray code sequence is not uniquely defined.
     * <p>
     * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
     *
     * @param n the bit number
     * @return an sequence of gray code
     */
    public List<Integer> grayCode(int n) {
        if (n == 0) return Collections.singletonList(0);
        else if (n < 0) return Collections.emptyList();
        int totalNum = 1 << n;
        Integer[] path = new Integer[totalNum];
        boolean[] isUsed = new boolean[totalNum];
        isUsed[0] = true;
        path[0] = 0;
        findPath = false;
        findGrayCodeArray(path, isUsed, 1, n);
        return Arrays.asList(path);
    }

    private void findGrayCodeArray(Integer[] path, boolean[] used, int index, int n) {
        if (findPath) return;
        else if (index == path.length) {
            findPath = true;
        } else {
            int lastInteger = path[index - 1];
            for (int i = 0; i < n; i++) {
                if (findPath) break;
                int bitOperation = 1 << i;
                int newInteger = ((bitOperation & lastInteger) == bitOperation) ? (lastInteger - bitOperation) :
                        (lastInteger + bitOperation);
                if (!used[newInteger]) {
                    path[index] = newInteger;
                    used[newInteger] = true;
                    findGrayCodeArray(path, used, index + 1, n);
                    used[newInteger] = false;
                }
            }
        }
    }

    /**
     * This way will TLE
     * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations
     * that add up to a positive integer target.
     * <p>
     * Example:
     * <p>
     * nums = [1, 2, 3]
     * target = 4
     * <p>
     * The possible combination ways are:
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * <p>
     * Note that different sequences are counted as different combinations.
     * <p>
     * Therefore the output is 7.
     *
     * @param nums   an integer array with all positive numbers and no duplicates
     * @param target a positive integer target
     * @return the number of different combinations
     */
    public int combinationSum4(int[] nums, int target) {
        combinationNum = 0;
        Arrays.sort(nums);
        getCombinationSum(nums, target);
        return combinationNum;
    }

    private void getCombinationSum(int[] nums, int target) {
        if (target <= 0) return;
        for (int num : nums) {
            if (num > target) break;
            else if (num == target) combinationNum++;
            else {
                getCombinationSum(nums, target - num);
            }
        }
    }
}
