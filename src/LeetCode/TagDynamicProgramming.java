package LeetCode;

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
}
