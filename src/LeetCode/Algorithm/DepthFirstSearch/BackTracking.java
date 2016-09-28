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
    private boolean isWordBreak;
    private HashSet<String> existingList;

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

    /**
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated
     * sequence of one or more dictionary words.
     * <p>
     * For example, given
     * s = "leetcode",
     * dict = ["leet", "code"].
     * <p>
     * Return true because "leetcode" can be segmented as "leet code".
     *
     * @param s        a string s
     * @param wordDict a word dict
     * @return check if s can be segmented into a space-separated sequence of one or more dictionary words
     */
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0) return true;
        isWordBreak = false;
        wordBreak(s.toCharArray(), wordDict, 0);
        return isWordBreak;
    }

    private void wordBreak(char[] chars, Set<String> wordDict, int index) {
        if (isWordBreak) return;
        else if (index == chars.length) isWordBreak = true;
        for (int i = index + 1, n = chars.length; i < n; i++) {
            String s = new String(chars, index, i - index);
            System.out.println(s);
            if (wordDict.contains(s)) {
                wordBreak(chars, wordDict, i + 1);
            }
            if (isWordBreak) break;
        }
    }

    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * <p>
     * For example, given n = 3, a solution set is:
     * <p>
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     *
     * @param n n pairs of parentheses
     * @return a solution set
     */
    public List<String> generateParenthesis(int n) {
        ArrayList<String> pareList = new ArrayList<>();
        if (n <= 0) return pareList;
        generateParenthesis(0, new char[n * 2], 0, pareList);
        return pareList;
    }

    private void generateParenthesis(int nLeft, char[] path, int index, ArrayList<String> result) {
        if (index == path.length && nLeft == 0) result.add(new String(path));
        else if (index < path.length) {
            if (nLeft == 0) {
                path[index] = '(';
                generateParenthesis(1, path, index + 1, result);
            } else if (index + nLeft == path.length) {
                path[index] = ')';
                generateParenthesis(nLeft - 1, path, index + 1, result);
            } else if (index + nLeft < path.length) {
                path[index] = ')';
                generateParenthesis(nLeft - 1, path, index + 1, result);
                path[index] = '(';
                generateParenthesis(nLeft + 1, path, index + 1, result);
            }
        }
    }

    /**
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the
     * candidate numbers sums to T.
     * <p>
     * Each number in C may only be used once in the combination.
     * <p>
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
     * A solution set is:
     * [
     * [1, 7],
     * [1, 2, 5],
     * [2, 6],
     * [1, 1, 6]
     * ]
     *
     * @param candidates a list of
     * @param target     target number
     * @return the list of combinations
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinationList = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return combinationList;
        Arrays.sort(candidates);
        existingList = new HashSet<>();
        backtrackingSearchCombinations(candidates, 0, target, new ArrayList<>(), combinationList);
        return combinationList;
    }

    private void backtrackingSearchCombinations(int[] candidates, int candidateIndex, int target,
                                                ArrayList<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            if (!existingList.contains(path.toString())) {
                result.add(new ArrayList<>(path));
                existingList.add(path.toString());
            }
        } else if (target < 0 || candidateIndex == candidates.length) {
            for (int i = candidateIndex; i < candidates.length; i++) {
                int candidate = candidates[i];
                if (candidate > target) break;
                path.add(candidate);
                backtrackingSearchCombinations(candidates, i + 1, target - candidate, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * <p>
     * For example:
     * Given "25525511135",
     * <p>
     * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     *
     * @param s a string containing only digits
     * @return all possible valid IP address combinations
     */
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<>();
        if (s == null || s.length() < 4) return result;
        restoreIpAddresses(s.toCharArray(), 0, result, new int[4], 0);
        return result;
    }

    private void restoreIpAddresses(char[] chars, int charIndex, ArrayList<String> result, int[] path, int pathIndex) {
        if (charIndex == chars.length) {
            if (pathIndex == 4) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    stringBuilder.append(path[i]);
                    stringBuilder.append('.');
                }
                stringBuilder.append(path[3]);
                result.add(stringBuilder.toString());
            }
        } else if (pathIndex <= 3) {
            int currentNum = 0;
            for (int i = charIndex, max = chars.length - 3 + pathIndex; i < max; i++) {
                currentNum = currentNum * 10 + chars[i] - '0';
                if (currentNum > 255) break;
                path[pathIndex] = currentNum;
                restoreIpAddresses(chars, i + 1, result, path, pathIndex + 1);
                if (currentNum == 0) break;
            }
        }
    }

    /**
     * The set [1,2,3,…,n] contains a total of n! unique permutations.
     * <p>
     * By listing and labeling all of the permutations in order,
     * We get the following sequence (ie, for n = 3):
     * <p>
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     * <p>
     * Note: Given n will be between 1 and 9 inclusive.
     *
     * @param n a total of n! unique permutations
     * @param k the index of the sequence
     * @return the kth permutation sequence
     */
    public String getPermutation(int n, int k) {
        permutationIndex = k;
        ArrayList<Character> remain = new ArrayList<>();
        for (char c = '1', i = 0; i < n; i++, c++) {
            remain.add(c);
        }
        char[] result = new char[n];
        getPermutation(remain, result, 0);
        return kThSequence;
    }

    private void getPermutation(ArrayList<Character> remains, char[] path, int index) {
        if (permutationIndex > 0) {
            if (remains.isEmpty()) {
                permutationIndex--;
                if (permutationIndex == 0) kThSequence = new String(path);
            } else {
                for (int i = 0, n = remains.size(); i < n; i++){
                    char c = remains.get(i);
                    remains.remove(i);
                    path[index] = c;
                    getPermutation(remains, path, index + 1);
                    remains.add(i, c);
                }
            }
        }
    }

    private int permutationIndex;
    private String kThSequence;
}
