package LeetCode.Algorithm.Array;

/**
 * Created by warn on 23/8/2016.
 * Store puzzles with tag two pointer
 */
public class TwoPointer {
    /**
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements
     * from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
     *
     * @param nums1 one sorted array
     * @param m     numbers in nums1
     * @param nums2 another sorted array
     * @param n     numbers in nums2
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 != null && nums2.length > 0 && n > 0) {
            int pointer1 = m - 1, pointer2 = n - 1;
            for (int i = m + n - 1; i >= 0; i--) {
                if (pointer1 < 0) {
                    System.arraycopy(nums2, 0, nums1, 0, pointer2 + 1);
                    break;
                } else if (pointer2 < 0) break;
                int n1 = nums1[pointer1], n2 = nums2[pointer2];
                if (n1 >= n2) {
                    nums1[i] = n1;
                    pointer1--;
                } else {
                    nums1[i] = n2;
                    pointer2--;
                }
            }
        }
    }
}
