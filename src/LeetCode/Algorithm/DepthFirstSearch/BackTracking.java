package LeetCode.Algorithm.DepthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by warn on 13/6/2016.
 * Store solution to test with backtracking
 */
public class BackTracking {
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
    private HashSet<String> contains;

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
}
