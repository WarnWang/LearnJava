package LeetCode.Algorithm.DynamicProgramming;

import java.util.Set;

/**
 * Created by warn on 31/7/2016.
 * <p>
 * Store some DP puzzles solution
 */
public class Solution2 {
    /**
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
        if (target <= 0) return 0;
        int[] combinationNum = new int[target + 1];
//        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num == i) combinationNum[i]++;
                else if (num < i) combinationNum[i] += combinationNum[i - num];
            }
        }
        return combinationNum[target];
    }

    /**
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence
     * of one or more dictionary words.
     * <p>
     * For example, given
     * s = "leetcode",
     * dict = ["leet", "code"].
     * <p>
     * Return true because "leetcode" can be segmented as "leet code".
     *
     * @param s a string s
     * @param wordDict a dictionary of words dict
     * @return if s can be segmented into a space-separated sequence of one or more dictionary words
     */
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0) return true;
        else if (wordDict == null || wordDict.size() == 0) return false;
        int n = s.length();
        boolean[] isInDict = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (wordDict.contains(s.substring(0, i + 1))) isInDict[i] = true;
            else {
                for (int j = i; j >= 1; j--) {
                    if (isInDict[j - 1] && wordDict.contains(s.substring(j, i + 1))) {
                        isInDict[i] = true;
                        break;
                    }
                }
            }
        }
        return isInDict[n - 1];
    }
}
