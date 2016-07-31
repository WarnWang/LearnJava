package LeetCode.Algorithm.DynamicProgramming;

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
}
