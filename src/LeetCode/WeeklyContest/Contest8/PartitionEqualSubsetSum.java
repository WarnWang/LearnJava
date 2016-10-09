package LeetCode.WeeklyContest.Contest8;

import java.util.ArrayList;

/**
 * Created by warn on 9/10/2016.
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets
 * such that the sum of elements in both subsets is equal.
 * <p>
 * Note:
 * Both the array size and each of the array element will not exceed 100.
 * <p>
 * Example 1:
 * <p>
 * Input: [1, 5, 11, 5]
 * <p>
 * Output: true
 * <p>
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 * <p>
 * Input: [1, 2, 3, 5]
 * <p>
 * Output: false
 * <p>
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        if ((totalSum % 2) == 1) return false;
        int target = totalSum / 2;
        return isPartition(nums, nums.length, target);

    }

    private boolean isPartition(int[] nums, int index, int target) {
        if (target == 0) return true;
        else if (index == 0) return false;
        if (nums[index - 1] > target) return isPartition(nums, index - 1, target);
        return isPartition(nums, index - 1, target - nums[index - 1]) || isPartition(nums, index - 1, target);
    }
}
