package LeetCode.Algorithm.Array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by warn on 1/6/2016.
 * Store solution to Array puzzles
 */
public class Solution {

    /**
     * There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted
     * arrays. The overall run time complexity should be O(log (m+n)).
     *
     * @param nums1 one sorted array
     * @param nums2 another sorted array
     * @return the median of target array
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1, n2;
        if (nums1 == null) n1 = 0;
        else n1 = nums1.length;
        if (nums2 == null) n2 = 0;
        else n2 = nums2.length;
        return findMedianSortedArrays(nums1, 0, n1, nums2, 0, n2);
    }

    private double findMedianSortedArrays(int[] nums1, int nums1Start, int nums1End,
                                          int[] nums2, int nums2Start, int nums2End) {
        if (nums1Start >= nums1End && nums2Start >= nums2End) return 0;
        else if (nums1Start >= nums1End) {
            return findMedianSortedArrays(nums2, nums2Start, nums2End);
        } else if (nums2Start >= nums2End) return findMedianSortedArrays(nums1, nums1Start, nums1End);
        while (true) {
            if (nums1Start >= nums1End) return findMedianSortedArrays(nums2, nums2Start, nums2End);
            else if (nums2Start >= nums2End) return findMedianSortedArrays(nums1, nums1Start, nums1End);
            if (nums1End - nums1Start + nums2End - nums2Start == 2)
                return (double) (nums1[nums1Start] + nums2[nums2Start]) / 2.0;
            if (nums1[nums1Start] > nums2[nums2Start]) nums2Start++;
            else nums1Start++;
            if (nums1[nums1End - 1] > nums2[nums2End - 1]) nums1End--;
            else nums2End--;
        }

    }

    private double findMedianSortedArrays(int[] nums, int start, int end) {
        if (start >= end) return 0;
        else if ((end - start) % 2 == 1) return nums[(start + end) / 2];
        else return (double) (nums[(start + end) / 2] + nums[(start + end) / 2 - 1]) / 2.0;
    }

    /**
     * Given a sorted integer array without duplicates, return the summary of its ranges.
     * <p>
     * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
     *
     * @param nums a sorted integer array without duplicates
     * @return return the summary of its ranges
     */
    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> rangeList = new ArrayList<>();
        if (nums == null || nums.length == 0) return rangeList;
        int start = nums[0];
        boolean inRange = false;
        for (int i = 0, n = nums.length; i < n; i++) {
            if (inRange) {
                if (i + 1 == n || nums[i + 1] != nums[i] + 1) {
                    inRange = false;
                    rangeList.add(start + "->" + nums[i]);
                }
            } else {
                start = nums[i];
                if (i + 1 == n || nums[i + 1] != nums[i] + 1) {
                    rangeList.add(Integer.toString(nums[i]));
                } else inRange = true;
            }
        }
        return rangeList;
    }
}