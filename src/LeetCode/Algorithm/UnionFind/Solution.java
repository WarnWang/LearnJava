package LeetCode.Algorithm.UnionFind;

import java.util.HashMap;

/**
 * Created by warn on 18/6/2016.
 * Store puzzles with tag union find
 */
public class Solution {
    public static void main(String args[]) {
        Solution test = new Solution();
        System.out.print(test.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    /**
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     * <p>
     * For example,
     * Given [100, 4, 200, 1, 3, 2],
     * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
     * <p>
     * Your algorithm should run in O(n) complexity.
     *
     * @param nums an unsorted array of integers
     * @return the longest consecutive elements sequence
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return 1;
        HashMap<Integer, int[]> rangeMap = new HashMap<>();
        int largestLength = 1;
        for (int num: nums) {
            if (rangeMap.containsKey(num)) continue;
            int start=num, end=num;
            if (rangeMap.containsKey(num - 1)) start = rangeMap.get(num - 1)[0];
            if (rangeMap.containsKey(num + 1)) end = rangeMap.get(num + 1)[1];
            largestLength = Math.max(end - start + 1, largestLength);
            int[] range = {start, end};
            rangeMap.put(start, range);
            rangeMap.put(end, range);
            rangeMap.put(num, range);
        }
        return largestLength;
    }
}
